package application.service.impl;

import application.entity.User;
import application.exception.UserNotFoundException;
import application.model.request.AuthenticationRequest;
import application.model.request.IntrospectRequest;
import application.model.response.AuthenticationResponse;
import application.model.response.IntrospectResponse;
import application.repository.UserRepository;
import application.service.AuthenticationService;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    private UserRepository userRepository;
    @Value("${jwt_signer_key}")
    private String SIGNER_KEY;

    @Override
    public IntrospectResponse introspect(IntrospectRequest request) {
        String token = request.getToken();
        try {
            JWSVerifier jwsVerifier = new MACVerifier(SIGNER_KEY.getBytes());
            System.out.println(token);
            SignedJWT signedJWT = SignedJWT.parse(token);
            boolean verified = signedJWT.verify(jwsVerifier);
            Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();
            boolean valid = verified && expiryTime.after(new Date());
            return new IntrospectResponse(valid);
        }
        catch (ParseException | JOSEException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        User user = userRepository.findByUsername(request.getUsername());
        if(user == null || !user.isEnabled()){
            throw new UserNotFoundException("USER NAME INVALID");
        }
        String token = generateToken(request.getUsername());
        return AuthenticationResponse.builder()
                .authenticated(true)
                .token(token)
                .build();
    }
    private String generateToken(String username){
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(username)
                .issuer("devtruong.com")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()
                ))
                .build();
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);
        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
    }
}

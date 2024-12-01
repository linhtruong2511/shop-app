package application.controller;

import application.model.request.AuthenticationRequest;
import application.model.request.IntrospectRequest;
import application.model.response.APIResponse;
import application.model.response.AuthenticationResponse;
import application.model.response.IntrospectResponse;
import application.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationAPI {
    @Autowired
    AuthenticationService authenticationService;
    @PostMapping(value = "auth/token")
    APIResponse<AuthenticationResponse> authentication(@RequestBody AuthenticationRequest request){
        AuthenticationResponse authenticationResponse =  authenticationService.authenticate(request);
        return APIResponse.<AuthenticationResponse>builder()
                .result(authenticationResponse)
                .build();
    }
    @PostMapping(value = "auth/introspect")
    APIResponse<IntrospectResponse> introspect(@RequestBody IntrospectRequest request){
        IntrospectResponse  introspectResponse = authenticationService.introspect(request);
        return APIResponse.<IntrospectResponse>builder()
                .result(introspectResponse)
                .build();
    }
}

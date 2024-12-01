package application.service;

import application.model.request.AuthenticationRequest;
import application.model.request.IntrospectRequest;
import application.model.response.AuthenticationResponse;
import application.model.response.IntrospectResponse;

public interface AuthenticationService {
    AuthenticationResponse authenticate(AuthenticationRequest request);
    IntrospectResponse introspect(IntrospectRequest request);
}

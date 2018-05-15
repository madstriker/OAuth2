package com.my.config;

import com.my.model.ClientsDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;


@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig  extends AuthorizationServerConfigurerAdapter{
        static final String CLIENT_ID="devglan-client";
        static final String CLIENT_SECRET="password";
        static final String GRANT_TYPE="password";
        static final String AUTHORIZATION_CODE="authorization_code";
        static final String REFRESH_TOKEN="refresh_token";
        static final String IMPLICIT="implicit";
        static final String SCOPE_READ="read";
        static final String SCOPE_WRITE="write";
        static final String TRUST="trust";
        static final int  ACCESS_TOKEN_VALIDITY_SECONDS = 1*60*60;
        static final int FREFRESH_TOKEN_VALIDITY_SECONDS = 6*60*60;


        @Autowired
        private ClientsDetails clientsDetails;


        @Autowired
        private AuthenticationManager authenticationManager;

        @Autowired
        private PasswordEncoder passwordEncoder;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.allowFormAuthenticationForClients();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        System.out.println("??????"+clientsDetails.getSecret());

        clients.inMemory().withClient(CLIENT_ID)
                .authorizedGrantTypes(GRANT_TYPE,AUTHORIZATION_CODE,REFRESH_TOKEN,IMPLICIT)
                .scopes(SCOPE_READ,SCOPE_WRITE,TRUST)
                .accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS)
                .refreshTokenValiditySeconds(FREFRESH_TOKEN_VALIDITY_SECONDS)
                .secret("{noop}secret");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager);
    }
}

package com.tsystems.oidc;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.oidc.TenantConfigResolver;
import io.quarkus.oidc.runtime.OidcTenantConfig;
import io.vertx.ext.web.RoutingContext;

@ApplicationScoped
public class CustomTenantConfigResolver implements TenantConfigResolver {

    @Override
    public OidcTenantConfig resolve(RoutingContext context) {
        String path = context.request().path();
        String[] parts = path.split("/");

        if (parts.length == 0) {
            // resolve to default tenant config
            return null;
        }

        if ("tenant-c".equals(parts[1])) {
            OidcTenantConfig config = new OidcTenantConfig();

            config.setTenantId("tenant-c");
            config.setAuthServerUrl("http://localhost:8180/auth/realms/tenant-c");
            config.setClientId("multi-tenant-client");
            OidcTenantConfig.Credentials credentials = new OidcTenantConfig.Credentials();

            credentials.setSecret("my-secret");

            config.setCredentials(credentials);

            // any other setting support by the quarkus-oidc extension

            return config;
        }
        return null;
    }
}
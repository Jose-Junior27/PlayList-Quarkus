package br.com.bb;


import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/api/v1")
@OpenAPIDefinition(
        info = @Info(
                title = "Play Lists - LetsCode",
                description = "Endpoint - PlayList",
                version = "1.000",
                contact = @Contact(name = "JJ", url = "jj.bb.com.br", email = "junior.jm.sj25@gmail.com")
        ))
public class APIDefinition extends Application {
}

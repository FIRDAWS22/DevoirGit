package com.commandes.configurations;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;
@Component
@ConfigurationProperties("mes-config-ms")
@RefreshScope

public class ApplicationPropertiesConfiguration {
    private int commandeslast ;

	public int getCommandeslast() {
		return commandeslast;
	}

	public void setCommandeslast(int commandeslast) {
		this.commandeslast = commandeslast;
	}
   
}

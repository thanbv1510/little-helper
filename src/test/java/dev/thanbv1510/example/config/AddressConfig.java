package dev.thanbv1510.example.config;

import dev.thanbv1510.littlehelper.configuration.annotation.PropertyField;
import dev.thanbv1510.littlehelper.configuration.annotation.PropertySource;
import lombok.Data;

@Data
@PropertySource(name = "application.yaml", prefix = "address")
public class AddressConfig {

    @PropertyField(name = "street1", defaultValue = "HB")
    private String street;

    private String city;
}

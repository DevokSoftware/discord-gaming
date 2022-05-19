package com.devok.games.geoguessr.api.positionstack;

import javax.json.bind.annotation.JsonbProperty;
import java.util.List;

public class AddressList {

    @JsonbProperty("data")
    private List<Address> addresses;

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }
}

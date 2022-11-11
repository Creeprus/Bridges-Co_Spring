package com.example.BridgeAndCoCursach.Models;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.security.core.GrantedAuthority;


public enum Role implements GrantedAuthority {
    Администратор, Логист, Кладовщик, Курьер, Клиент, Поставщик;
    @Override
    public String getAuthority() {
        return name();
    }

}

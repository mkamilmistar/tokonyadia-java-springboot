package com.enigma.tokonyadiaboot.service;

import java.util.Map;

public interface AppConfigService {

    public String getValue(String key);
    public void setValue(String key, Object value);
}

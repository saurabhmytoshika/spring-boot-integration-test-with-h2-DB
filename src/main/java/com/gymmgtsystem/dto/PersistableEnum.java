package com.gymmgtsystem.dto;

import java.io.Serializable;

public interface PersistableEnum<T> extends Serializable {
    T getValue();
}

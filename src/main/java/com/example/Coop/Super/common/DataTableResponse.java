package com.example.Coop.Super.common;

import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
@ToString
public class DataTableResponse<T> {

    public List<T> data = new ArrayList<>();
    public DataTableResponse(){
    }
}

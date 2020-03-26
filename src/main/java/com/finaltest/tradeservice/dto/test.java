package com.finaltest.tradeservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public class test {

    private String name;
    private String angka;

    @JsonProperty("listItem")
    private List<Map<String, Object>> listItem;

    public List<Map<String, Object>> getListItem() {
        return listItem;
    }

    public void setListItem(List<Map<String, Object>> listItem) {
        this.listItem = listItem;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAngka() {
        return angka;
    }

    public void setAngka(String angka) {
        this.angka = angka;
    }

}

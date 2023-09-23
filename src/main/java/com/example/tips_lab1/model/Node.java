package com.example.tips_lab1.model;

import java.util.Objects;

public class Node {
    private String label;
    private Double probabilityChannelFailure;

    private Double probabilityNodeFailure ;

    public Node(String label) {
        this.label = label;
        probabilityNodeFailure = 0.0;
        probabilityChannelFailure = 0.0;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Double getProbabilityChannelFailure() {
        return probabilityChannelFailure;
    }

    public void setProbabilityChannelFailure(Double probabilityChannelFailure) {
        this.probabilityChannelFailure = probabilityChannelFailure;
    }

    public Double getProbabilityNodeFailure() {
        return probabilityNodeFailure;
    }

    public void setProbabilityNodeFailure(Double probabilityNodeFailure) {
        this.probabilityNodeFailure = probabilityNodeFailure;
    }


}
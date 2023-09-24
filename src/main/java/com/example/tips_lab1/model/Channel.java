package com.example.tips_lab1.model;

import java.util.List;
import java.util.Objects;

public class Channel {
    private List<Node> nodes;
    private Double probabilityFailure;
    private final String label;
    private double transmission;

    public Channel(List<Node> nodes, String label) {
        this.nodes = nodes;
        this.label = label;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public Double getProbabilityFailure() {
        return probabilityFailure;
    }

    public void setProbabilityFailure(Double probabilityFailure) {
        this.probabilityFailure = probabilityFailure;
    }

    public String getLabel() {
        return label;
    }

    public double getTransmission() {
        return transmission;
    }

    public void setTransmission(double transmission) {
        this.transmission = transmission;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Channel channel = (Channel) o;
        return Objects.equals(label, channel.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(label);
    }
}

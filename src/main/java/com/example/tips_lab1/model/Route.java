package com.example.tips_lab1.model;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Route {
    private List<Node> route;
    private int countChannel;
    private final String routeStr;

    private List<Channel> channels;
    public Route(List<Node> route, int countChannel, String routeStr, List<Channel> channels) {
        this.route = route;
        this.countChannel = countChannel;
        this.routeStr = routeStr;
        this.channels = channels;
    }

    public List<Node> getRoute() {
        return route;
    }

    public String getRouteStr() {
        return routeStr;
    }
    public void setRouteStr(List<Node> routeStr) {
        this.route = routeStr;
    }

    public int getCountChannel() {
        return countChannel;
    }

    public void setCountChannel(int countChannel) {
        this.countChannel = countChannel;
    }

    public List<Channel> getChannel() {
        return channels;
    }

    public void setChannel(List<Channel> channel) {
        this.channels = channel;
    }

    public double getProbabilityOfRoute() {
        double result = 1.0;
        for(int i = 0; i<route.size() - 1;i++) {
            Node startNode = route.get(i);
            Node endNode = route.get(i + 1);
            if(checkNodeAndChannel(startNode) && checkChannel(startNode,endNode)){
                result *= (1 - startNode.getProbabilityNodeFailure()) * (1 - getProbabilityChannel(startNode, endNode));
            } else {
                return 0.0;
            }

        }
        result *= (1 - route.get(route.size()-1).getProbabilityNodeFailure());
        return 1.0 - result;
    }

    public boolean checkNodeAndChannel(Node node) {
        return node.getProbabilityNodeFailure() != 0;
    }

    public boolean checkChannel(Node nodeStart, Node nodeEnd) {
        Optional<Channel> findChannel = channels.stream().filter(c -> c.getNodes().contains(nodeStart) && c.getNodes().contains(nodeEnd)).findFirst();
        return findChannel.isPresent();
    }

    public void setRoute(List<Node> route) {
        this.route = route;
    }

    private double getProbabilityChannel(Node nodeStart, Node nodeEnd) {
        Optional<Channel> findChannel = channels.stream().filter(c -> c.getNodes().contains(nodeStart) && c.getNodes().contains(nodeEnd)).findFirst();
        return findChannel.isPresent() ? findChannel.get().getProbabilityFailure() : 0.0;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return Objects.equals(routeStr, route.routeStr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(routeStr);
    }
}

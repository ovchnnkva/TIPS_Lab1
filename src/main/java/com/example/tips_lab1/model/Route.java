package com.example.tips_lab1.model;

import java.util.List;
import java.util.Objects;

public class Route {
    private List<Node> route;
    private int countChannel;
    private final String routeStr;

    public Route(List<Node> route, int countChannel, String routeStr) {
        this.route = route;
        this.countChannel = countChannel;
        this.routeStr = routeStr;
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

    public double getProbabilityOfRoute() {
        double result = 1.0;
        System.out.println("node size " + route.size());
        for(int i = 0; i<route.size();i++) {
            Node node = route.get(i);
            if(checkNode(node)){
                if(i == route.size() - 1) {
                    result *= (1 - node.getProbabilityNodeFailure());
                } else {
                    result *= (1 - node.getProbabilityNodeFailure()) * (1 - node.getProbabilityChannelFailure());
                }
                System.out.println(routeStr + " node " + node.getLabel() + " channel probability = " + node.getProbabilityChannelFailure() + " node probability " + node.getProbabilityNodeFailure());
            } else {
                return 0.0;
            }
        }
        System.out.println("route " + routeStr + " probability " + result);
        return 1.0 - result;
    }

    public boolean checkNode(Node node) {
        return node.getProbabilityNodeFailure() != 0 && node.getProbabilityChannelFailure() != 0;
    }

    public void setRoute(List<Node> route) {
        this.route = route;
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

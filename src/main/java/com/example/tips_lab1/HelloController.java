package com.example.tips_lab1;

import com.example.tips_lab1.formatter.StringToDoubleFormatter;
import com.example.tips_lab1.formatter.StringToRouteStringFormatter;
import com.example.tips_lab1.model.Node;
import com.example.tips_lab1.model.Route;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class HelloController {

    @FXML
    public TextField messageAddressing;
    @FXML
    public Button addRoute;
    @FXML
    public TextField route;
    @FXML
    public Button node1;
    @FXML
    public Button node2;
    @FXML
    public Button node3;
    @FXML
    public Button node4;
    @FXML
    public Button clearRoute;

    //Основные
    @FXML
    private TextField avgRecoveryChannel;

    @FXML
    private TextField avgRecoveryNode;

    @FXML
    private TextField avgLengthPackage;
    //Связи
    @FXML
    public TextField routeFailure;
    @FXML
    public TextField normalRouteFailure;

    @FXML
    private ComboBox<String> connectionSellect;

    // Узел/Канал 1
    @FXML
    public TextField probabilityChannel1Failure;
    @FXML
    public TextField probabilityNode1Failure;
    @FXML
    public TextField recoveryTimeNode1;
    @FXML
    public TextField modulationRateChannel1;
    @FXML
    public TextField countInTheBundleChannel1;
    @FXML
    public TextField recoveryTimeChannel1;
    @FXML
    public TextField avgLengthPackageChannel1;

    // Узел/Канал 2
    @FXML
    public TextField probabilityChannel2Failure;
    @FXML
    public TextField probabilityNode2Failure;
    @FXML
    public TextField recoveryTimeNode2;
    @FXML
    public TextField countInTheBundleChannel2;
    @FXML
    public TextField recoveryTimeChannel2;
    @FXML
    public TextField modulationRateChannel2;
    @FXML
    public TextField avgLengthPackageChannel2;
    // Узел/Канал 3
    @FXML
    public TextField probabilityChannel3Failure;
    @FXML
    public TextField probabilityNode3Failure;
    @FXML
    public TextField recoveryTimeNode3;
    @FXML
    public TextField countInTheBundleChannel3;
    @FXML
    public TextField recoveryTimeChannel3;
    @FXML
    public TextField modulationRateChannel3;
    @FXML
    public TextField avgLengthPackageChannel3;
    // Узел/Канал 4
    @FXML
    public TextField probabilityChannel4Failure;
    @FXML
    public TextField probabilityNode4Failure;
    @FXML
    public TextField recoveryTimeNode4;
    @FXML
    public TextField countInTheBundleChannel4;
    @FXML
    public TextField recoveryTimeChannel4;
    @FXML
    public TextField modulationRateChannel4;
    @FXML
    public TextField avgLengthPackageChannel4;

    private List<Route> routes;
    private List<Node> nodes;
    private final DecimalFormat df = new DecimalFormat("#.#####");


    @FXML
    private void initialize() {
        routes = new ArrayList<>();
        nodes = new ArrayList<>();

        nodes.addAll(Arrays.asList(new Node("1"), new Node("2"), new Node("3"), new Node("4")));

        configureBaseTab();
        configureCommunications();

        configureNodeAndChannel1Tab();
        configureNodeAndChannel2Tab();
        configureNodeAndChannel3Tab();
        configureNodeAndChannel4Tab();

        configureNodeButton();
        configureRoutesButton();
    }

    private void configureNodeButton() {
        node1.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            String str = route.getText().isBlank() ? node1.getText() : "->" + node1.getText();
            route.appendText(str);
        });

        node2.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            String str = route.getText().isBlank() ? node2.getText() : "->" + node2.getText();
            route.appendText(str);
        });
        node3.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            String str = route.getText().isBlank() ? node3.getText() : "->" + node3.getText();
            route.appendText(str);
        });

        node4.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            String str = route.getText().isBlank() ? node4.getText() : "->" + node4.getText();
            route.appendText(str);
        });
    }

    private void configureRoutesButton() {
        clearRoute.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            route.setText("");
        });

        addRoute.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            if(!route.getText().isBlank()) {
                routes.add(stringToRoute(route.getText()));
                connectionSellect.itemsProperty().setValue(FXCollections.observableArrayList(routes.stream().map(Route::getRouteStr).collect(Collectors.toList())));
                route.setText("");
            }
        });
    }

    private void configureBaseTab() {
        avgRecoveryChannel.setTextFormatter(new StringToDoubleFormatter().getTextFormatter());
        avgLengthPackage.setTextFormatter(new StringToDoubleFormatter().getTextFormatter());
        avgRecoveryNode.setTextFormatter(new StringToDoubleFormatter().getTextFormatter());
        messageAddressing.setTextFormatter(new StringToRouteStringFormatter().getTextFormatter());
    }

    private void configureCommunications() {
        connectionSellect.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> changeProbabilityOfRoute(newValue));
    }
    private void configureNodeAndChannel1Tab() {
        probabilityChannel1Failure.setTextFormatter(new StringToDoubleFormatter().getTextFormatter());
        probabilityNode1Failure.setTextFormatter(new StringToDoubleFormatter().getTextFormatter());
        recoveryTimeNode1.setTextFormatter(new StringToDoubleFormatter().getTextFormatter());
        modulationRateChannel1.setTextFormatter(new StringToDoubleFormatter().getTextFormatter());
        countInTheBundleChannel1.setTextFormatter(new StringToDoubleFormatter().getTextFormatter());
        recoveryTimeChannel1.setTextFormatter(new StringToDoubleFormatter().getTextFormatter());
        avgLengthPackageChannel1.setTextFormatter(new StringToDoubleFormatter().getTextFormatter());

        probabilityChannel1Failure.textProperty().addListener((observableValue, oldValue, newValue) ->
                channelFailureChanged("1", newValue));

        probabilityNode1Failure.textProperty().addListener(((observableValue, oldValue, newValue) ->
                nodeFailureChanged("1", newValue)));
    }
    private void configureNodeAndChannel2Tab() {
        probabilityChannel2Failure.setTextFormatter(new StringToDoubleFormatter().getTextFormatter());
        probabilityNode2Failure.setTextFormatter(new StringToDoubleFormatter().getTextFormatter());
        recoveryTimeNode2.setTextFormatter(new StringToDoubleFormatter().getTextFormatter());
        modulationRateChannel2.setTextFormatter(new StringToDoubleFormatter().getTextFormatter());
        countInTheBundleChannel2.setTextFormatter(new StringToDoubleFormatter().getTextFormatter());
        recoveryTimeChannel2.setTextFormatter(new StringToDoubleFormatter().getTextFormatter());
        avgLengthPackageChannel2.setTextFormatter(new StringToDoubleFormatter().getTextFormatter());

        probabilityChannel2Failure.textProperty().addListener((observableValue, oldValue, newValue) ->
                channelFailureChanged("2", newValue));

        probabilityNode2Failure.textProperty().addListener(((observableValue, oldValue, newValue) ->
                nodeFailureChanged("2", newValue)));

    }
    private void configureNodeAndChannel3Tab() {
        probabilityChannel3Failure.setTextFormatter(new StringToDoubleFormatter().getTextFormatter());
        probabilityNode3Failure.setTextFormatter(new StringToDoubleFormatter().getTextFormatter());
        recoveryTimeNode3.setTextFormatter(new StringToDoubleFormatter().getTextFormatter());
        modulationRateChannel3.setTextFormatter(new StringToDoubleFormatter().getTextFormatter());
        countInTheBundleChannel3.setTextFormatter(new StringToDoubleFormatter().getTextFormatter());
        recoveryTimeChannel3.setTextFormatter(new StringToDoubleFormatter().getTextFormatter());
        avgLengthPackageChannel3.setTextFormatter(new StringToDoubleFormatter().getTextFormatter());

        probabilityChannel3Failure.textProperty().addListener((observableValue, oldValue, newValue) ->
                channelFailureChanged("3", newValue));

        probabilityNode3Failure.textProperty().addListener(((observableValue, oldValue, newValue) ->
                nodeFailureChanged("3", newValue)));

    }
    private void configureNodeAndChannel4Tab() {
        probabilityChannel4Failure.setTextFormatter(new StringToDoubleFormatter().getTextFormatter());
        probabilityNode4Failure.setTextFormatter(new StringToDoubleFormatter().getTextFormatter());
        recoveryTimeNode4.setTextFormatter(new StringToDoubleFormatter().getTextFormatter());
        modulationRateChannel4.setTextFormatter(new StringToDoubleFormatter().getTextFormatter());
        countInTheBundleChannel4.setTextFormatter(new StringToDoubleFormatter().getTextFormatter());
        recoveryTimeChannel4.setTextFormatter(new StringToDoubleFormatter().getTextFormatter());
        avgLengthPackageChannel4.setTextFormatter(new StringToDoubleFormatter().getTextFormatter());

        probabilityChannel4Failure.textProperty().addListener((observableValue, oldValue, newValue) ->
                channelFailureChanged("4", newValue));


        probabilityNode4Failure.textProperty().addListener(((observableValue, s, t1) ->
                nodeFailureChanged("4", t1)));
    }
    private Route stringToRoute(String strRoute) {
        List<String> nodesStrList = Arrays.asList(strRoute.split("->"));
        List<Node> nodesList = new ArrayList<>();
        for(String node : nodesStrList) {
            nodes.stream().filter(n -> n.getLabel().equals(node)).findFirst().ifPresent(nodesList::add);
        }
        return new Route(nodesList, nodesStrList.size()-1, strRoute);
    }

    private List<Node> stringToNodes(String strRoute) {
        String[] nodesStr = strRoute.split("->");
        List<Node> nodesResult = new ArrayList<>();

        for (String n:nodesStr) {
            Optional<Node> findNode = nodes.stream().filter(node -> node.getLabel().equals(n)).findFirst();
            findNode.ifPresent(nodesResult::add);
        }
        return nodesResult;
    }

    private void nodeFailureChanged(String labelNode, String value) {
        nodes.get(Integer.parseInt(labelNode) - 1).setProbabilityNodeFailure(Double.valueOf(value));
        System.out.println("node " + labelNode + " value " + value);
        List<Route> routeWithNode = routes.stream().filter(r -> r.getRouteStr().contains(labelNode)).toList();

        if(!routeWithNode.isEmpty()) {
            routeWithNode.forEach(this::updateNodesInRoute);
        }
    }

    private void channelFailureChanged(String labelNode, String value) {
        nodes.get(Integer.parseInt(labelNode) - 1).setProbabilityChannelFailure(Double.valueOf(value));

        List<Route> routeWithNode = routes.stream().filter(r -> r.getRouteStr().contains(labelNode)).toList();

        if(!routeWithNode.isEmpty()) {
            routeWithNode.forEach(this::updateNodesInRoute);
        }
    }

    private void changeProbabilityOfRoute(String routeStr) {
        Optional<Route> findRoute = routes.stream().filter(r -> r.getRouteStr().equals(routeStr)).findFirst();
        if(findRoute.isPresent()) {
            routeFailure.setText(df.format(findRoute.get().getProbabilityOfRoute()));
            normalRouteFailure.setText(df.format(getNormalProbabilityOfRoutes(findRoute.get().getProbabilityOfRoute())));
        }

    }

    private void updateNodesInRoute(Route routeForUpdate) {
        routes.stream().filter(r -> r.equals(routeForUpdate)).findFirst().ifPresent(r -> {
            r.setRoute(stringToNodes(routeForUpdate.getRouteStr()));
        });
    }
    private double getNormalProbabilityOfRoutes(double routeProbability) {
        double sum = 0.0;
        for(Route r : routes) {
            if(r.getProbabilityOfRoute() == 0){
                return 0;
            } else {
                sum += r.getProbabilityOfRoute();
            }
        }

        return routeProbability/sum;
    }

}
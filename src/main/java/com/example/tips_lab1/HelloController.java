package com.example.tips_lab1;

import com.example.tips_lab1.formatter.StringToDoubleFormatter;
import com.example.tips_lab1.formatter.StringToRouteStringFormatter;
import com.example.tips_lab1.model.Channel;
import com.example.tips_lab1.model.Node;
import com.example.tips_lab1.model.Route;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;
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
    private TextField avgLengthMessage;

    @FXML
    private TextField intesityMessage;

    @FXML
    private TextField minDeliveryTime;
    //Связи
    @FXML
    public TextField routeFailure;
    @FXML
    public TextField normalRouteFailure;
    @FXML
    private ComboBox<String> connectionSellect;
    @FXML
    private TextField intensityNode1;
    @FXML
    private TextField intensityNode2;
    @FXML
    private TextField intensityNode3;
    @FXML
    private TextField intensityNode4;
    @FXML
    private TextField delayTimeNode1;
    @FXML
    private TextField delayTimeNode2;
    @FXML
    private TextField delayTimeNode3;
    @FXML
    private TextField delayTimeNode4;
    @FXML
    private TextField countMessageNode1;
    @FXML
    private TextField countMessageNode2;
    @FXML
    private TextField countMessageNode3;
    @FXML
    private TextField countMessageNode4;
    @FXML
    private TextField avgDelayTime;
    @FXML
    private TextField avgTransferTime;

    //Узел 1
    @FXML
    public TextField probabilityNode1Failure;
    @FXML
    public TextField recoveryTimeNode1;

    // Узел 2
    @FXML
    public TextField probabilityNode2Failure;
    @FXML
    public TextField recoveryTimeNode2;
    // Узел 3
    @FXML
    public TextField probabilityNode3Failure;
    @FXML
    public TextField recoveryTimeNode3;
    // Узел 4
    @FXML
    public TextField probabilityNode4Failure;
    @FXML
    public TextField recoveryTimeNode4;


    // Канал 1
    @FXML
    public TextField probabilityChannel1Failure;
    @FXML
    public TextField modulationRateChannel1;
    @FXML
    public TextField countInTheBundleChannel1;
    @FXML
    public TextField recoveryTimeChannel1;
    @FXML
    public TextField avgLengthPackageChannel1;
    @FXML
    public TextField avgTransmissionTimeChannel1;

    // Канал 2
    @FXML
    public TextField probabilityChannel2Failure;
    @FXML
    public TextField countInTheBundleChannel2;
    @FXML
    public TextField recoveryTimeChannel2;
    @FXML
    public TextField modulationRateChannel2;
    @FXML
    public TextField avgLengthPackageChannel2;
    @FXML
    public TextField avgTransmissionTimeChannel2;
    // Канал 3
    @FXML
    public TextField probabilityChannel3Failure;
    @FXML
    public TextField countInTheBundleChannel3;
    @FXML
    public TextField recoveryTimeChannel3;
    @FXML
    public TextField modulationRateChannel3;
    @FXML
    public TextField avgLengthPackageChannel3;
    @FXML
    public TextField avgTransmissionTimeChannel3;
    // Канал 4
    @FXML
    public TextField probabilityChannel4Failure;
    @FXML
    public TextField countInTheBundleChannel4;
    @FXML
    public TextField recoveryTimeChannel4;
    @FXML
    public TextField modulationRateChannel4;
    @FXML
    public TextField avgLengthPackageChannel4;
    @FXML
    public TextField avgTransmissionTimeChannel4;
    //Канал 5
    @FXML
    public TextField probabilityChannel5Failure;
    @FXML
    public TextField countInTheBundleChannel5;
    @FXML
    public TextField recoveryTimeChannel5;
    @FXML
    public TextField modulationRateChannel5;
    @FXML
    public TextField avgLengthPackageChannel5;
    @FXML
    public TextField avgTransmissionTimeChannel5;
    //Канал 6
    @FXML
    public TextField probabilityChannel6Failure;
    @FXML
    public TextField countInTheBundleChannel6;
    @FXML
    public TextField recoveryTimeChannel6;
    @FXML
    public TextField modulationRateChannel6;
    @FXML
    public TextField avgLengthPackageChannel6;
    @FXML
    public TextField avgTransmissionTimeChannel6;


    private List<Route> routes;
    private List<Node> nodes;
    private List<Channel> channels;
    private DecimalFormat df;


    @FXML
    private void initialize() {
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.getDefault());
        otherSymbols.setDecimalSeparator('.');
        otherSymbols.setGroupingSeparator('.');
        df = new DecimalFormat("#.#####", otherSymbols);

        routes = new ArrayList<>();
        nodes = new ArrayList<>();
        channels = new ArrayList<>();

        nodes.addAll(Arrays.asList(new Node("1"), new Node("2"), new Node("3"), new Node("4")));

        configureChannels();

        configureBaseTab();
        configureCommunications();

        configureNodes();

        configureChannel1Tab();
        configureChannel2Tab();
        configureChannel3Tab();
        configureChannel4Tab();
        configureChannel5Tab();
        configureChannel6Tab();

        configureNodeButton();
        configureRoutesButton();
    }

    private void configureChannels() {
        List<Channel> channelsList = new ArrayList<>();
        channelsList.add(new Channel(Arrays.asList(nodes.get(0), nodes.get(1)), "1"));
        channelsList.add(new Channel(Arrays.asList(nodes.get(1), nodes.get(2)), "2"));
        channelsList.add(new Channel(Arrays.asList(nodes.get(0), nodes.get(2)), "3"));
        channelsList.add(new Channel(Arrays.asList(nodes.get(0), nodes.get(3)), "4"));
        channelsList.add(new Channel(Arrays.asList(nodes.get(1), nodes.get(3)), "5"));
        channelsList.add(new Channel(Arrays.asList(nodes.get(2), nodes.get(3)), "6"));

        channels.addAll(channelsList);
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
        avgLengthMessage.setTextFormatter(new StringToDoubleFormatter().getTextFormatter());
        minDeliveryTime.setTextFormatter(new StringToDoubleFormatter().getTextFormatter());
        intesityMessage.setTextFormatter(new StringToDoubleFormatter().getTextFormatter());
        messageAddressing.setTextFormatter(new StringToRouteStringFormatter().getTextFormatter());
    }

    private void configureCommunications() {
        connectionSellect.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            clearIntensity();
            clearDelayTime();
            clearCountMessage();
            changeProbabilityOfRoute(newValue);
        });
    }

    private void configureNodes() {
        probabilityNode1Failure.setTextFormatter(new StringToDoubleFormatter().getTextFormatter());
        recoveryTimeNode1.setTextFormatter(new StringToDoubleFormatter().getTextFormatter());

        probabilityNode1Failure.textProperty().addListener(((observableValue, oldValue, newValue) ->
                nodeFailureChanged("1", newValue)));

        probabilityNode2Failure.setTextFormatter(new StringToDoubleFormatter().getTextFormatter());
        recoveryTimeNode2.setTextFormatter(new StringToDoubleFormatter().getTextFormatter());

        probabilityNode2Failure.textProperty().addListener(((observableValue, oldValue, newValue) ->
                nodeFailureChanged("2", newValue)));

        probabilityNode3Failure.setTextFormatter(new StringToDoubleFormatter().getTextFormatter());
        recoveryTimeNode3.setTextFormatter(new StringToDoubleFormatter().getTextFormatter());

        probabilityNode3Failure.textProperty().addListener(((observableValue, oldValue, newValue) ->
                nodeFailureChanged("3", newValue)));

        probabilityNode4Failure.setTextFormatter(new StringToDoubleFormatter().getTextFormatter());
        recoveryTimeNode4.setTextFormatter(new StringToDoubleFormatter().getTextFormatter());

        probabilityNode4Failure.textProperty().addListener(((observableValue, s, t1) ->
                nodeFailureChanged("4", t1)));
    }
    private void configureChannel1Tab() {
        probabilityChannel1Failure.setTextFormatter(new StringToDoubleFormatter().getTextFormatter());
        modulationRateChannel1.setTextFormatter(new StringToDoubleFormatter().getTextFormatter());
        countInTheBundleChannel1.setTextFormatter(new StringToDoubleFormatter().getTextFormatter());
        recoveryTimeChannel1.setTextFormatter(new StringToDoubleFormatter().getTextFormatter());
        avgLengthPackageChannel1.setTextFormatter(new StringToDoubleFormatter().getTextFormatter());

        probabilityChannel1Failure.textProperty().addListener((observableValue, oldValue, newValue) -> {
            channels.stream().filter(c->c.getLabel().equals("1")).findFirst().ifPresent(c -> channelFailureChanged(c, newValue));
        });

        modulationRateChannel1.textProperty().addListener(((observableValue, oldValue, newValue) -> {
            double result = calculateAvgTransmission(Double.parseDouble(avgLengthPackageChannel1.getText()), Double.parseDouble(modulationRateChannel1.getText()), Double.parseDouble(countInTheBundleChannel1.getText()));
            avgTransmissionTimeChannel1.setText(df.format(result));
        }));
        avgLengthPackageChannel1.textProperty().addListener(((observableValue, oldValue, newValue) -> {
            double result = calculateAvgTransmission(Double.parseDouble(avgLengthPackageChannel1.getText()), Double.parseDouble(modulationRateChannel1.getText()), Double.parseDouble(countInTheBundleChannel1.getText()));
            avgTransmissionTimeChannel1.setText(df.format(result));
        }));
        countInTheBundleChannel1.textProperty().addListener(((observableValue, oldValue, newValue) -> {
            double result = calculateAvgTransmission(Double.parseDouble(avgLengthPackageChannel1.getText()), Double.parseDouble(modulationRateChannel1.getText()), Double.parseDouble(countInTheBundleChannel1.getText()));
            avgTransmissionTimeChannel1.setText(df.format(result));
        }));

        avgTransmissionTimeChannel1.textProperty().addListener((observable, oldValue, newValue) -> {
            channels.get(0).setTransmission(Double.parseDouble(newValue));
        });
    }
    private void configureChannel2Tab() {
        probabilityChannel2Failure.setTextFormatter(new StringToDoubleFormatter().getTextFormatter());
        modulationRateChannel2.setTextFormatter(new StringToDoubleFormatter().getTextFormatter());
        countInTheBundleChannel2.setTextFormatter(new StringToDoubleFormatter().getTextFormatter());
        recoveryTimeChannel2.setTextFormatter(new StringToDoubleFormatter().getTextFormatter());
        avgLengthPackageChannel2.setTextFormatter(new StringToDoubleFormatter().getTextFormatter());

        probabilityChannel2Failure.textProperty().addListener((observableValue, oldValue, newValue) -> {
                channels.stream().filter(c->c.getLabel().equals("2")).findFirst().ifPresent(c -> channelFailureChanged(c, newValue));
    });

        modulationRateChannel2.textProperty().addListener(((observableValue, oldValue, newValue) -> {
            double result = calculateAvgTransmission(Double.parseDouble(avgLengthPackageChannel2.getText()), Double.parseDouble(modulationRateChannel2.getText()), Double.parseDouble(countInTheBundleChannel2.getText()));
            avgTransmissionTimeChannel2.setText(df.format(result));
        }));
        avgLengthPackageChannel2.textProperty().addListener(((observableValue, oldValue, newValue) -> {
            double result = calculateAvgTransmission(Double.parseDouble(avgLengthPackageChannel2.getText()), Double.parseDouble(modulationRateChannel2.getText()), Double.parseDouble(countInTheBundleChannel2.getText()));
            avgTransmissionTimeChannel2.setText(df.format(result));
        }));
        countInTheBundleChannel2.textProperty().addListener(((observableValue, oldValue, newValue) -> {
            double result = calculateAvgTransmission(Double.parseDouble(avgLengthPackageChannel2.getText()), Double.parseDouble(modulationRateChannel2.getText()), Double.parseDouble(countInTheBundleChannel2.getText()));
            avgTransmissionTimeChannel2.setText(df.format(result));
        }));

        avgTransmissionTimeChannel2.textProperty().addListener((observable, oldValue, newValue) -> {
            channels.get(1).setTransmission(Double.parseDouble(newValue));
        });
    }
    private void configureChannel3Tab() {
        probabilityChannel3Failure.setTextFormatter(new StringToDoubleFormatter().getTextFormatter());
        modulationRateChannel3.setTextFormatter(new StringToDoubleFormatter().getTextFormatter());
        countInTheBundleChannel3.setTextFormatter(new StringToDoubleFormatter().getTextFormatter());
        recoveryTimeChannel3.setTextFormatter(new StringToDoubleFormatter().getTextFormatter());
        avgLengthPackageChannel3.setTextFormatter(new StringToDoubleFormatter().getTextFormatter());

        probabilityChannel3Failure.textProperty().addListener((observableValue, oldValue, newValue) -> {
                channels.stream().filter(c->c.getLabel().equals("3")).findFirst().ifPresent(c -> channelFailureChanged(c, newValue));
        });

        modulationRateChannel3.textProperty().addListener(((observableValue, oldValue, newValue) -> {
            double result = calculateAvgTransmission(Double.parseDouble(avgLengthPackageChannel3.getText()), Double.parseDouble(modulationRateChannel3.getText()), Double.parseDouble(countInTheBundleChannel3.getText()));
            avgTransmissionTimeChannel3.setText(df.format(result));
        }));
        avgLengthPackageChannel3.textProperty().addListener(((observableValue, oldValue, newValue) -> {
            double result = calculateAvgTransmission(Double.parseDouble(avgLengthPackageChannel3.getText()), Double.parseDouble(modulationRateChannel3.getText()), Double.parseDouble(countInTheBundleChannel3.getText()));
            avgTransmissionTimeChannel3.setText(df.format(result));
        }));
        countInTheBundleChannel3.textProperty().addListener(((observableValue, oldValue, newValue) -> {
            double result = calculateAvgTransmission(Double.parseDouble(avgLengthPackageChannel3.getText()), Double.parseDouble(modulationRateChannel3.getText()), Double.parseDouble(countInTheBundleChannel3.getText()));
            avgTransmissionTimeChannel3.setText(df.format(result));
        }));

        avgTransmissionTimeChannel3.textProperty().addListener((observable, oldValue, newValue) -> {
            channels.get(2).setTransmission(Double.parseDouble(newValue));
        });
    }
    private void configureChannel4Tab() {
        probabilityChannel4Failure.setTextFormatter(new StringToDoubleFormatter().getTextFormatter());
        modulationRateChannel4.setTextFormatter(new StringToDoubleFormatter().getTextFormatter());
        countInTheBundleChannel4.setTextFormatter(new StringToDoubleFormatter().getTextFormatter());
        recoveryTimeChannel4.setTextFormatter(new StringToDoubleFormatter().getTextFormatter());
        avgLengthPackageChannel4.setTextFormatter(new StringToDoubleFormatter().getTextFormatter());

        probabilityChannel4Failure.textProperty().addListener((observableValue, oldValue, newValue) -> {
            channels.stream().filter(c->c.getLabel().equals("4")).findFirst().ifPresent(c -> channelFailureChanged(c, newValue));
        });

        modulationRateChannel4.textProperty().addListener(((observableValue, oldValue, newValue) -> {
            double result = calculateAvgTransmission(Double.parseDouble(avgLengthPackageChannel4.getText()), Double.parseDouble(modulationRateChannel4.getText()), Double.parseDouble(countInTheBundleChannel4.getText()));
            avgTransmissionTimeChannel4.setText(df.format(result));
        }));
        avgLengthPackageChannel4.textProperty().addListener(((observableValue, oldValue, newValue) -> {
            double result = calculateAvgTransmission(Double.parseDouble(avgLengthPackageChannel4.getText()), Double.parseDouble(modulationRateChannel4.getText()), Double.parseDouble(countInTheBundleChannel4.getText()));
            avgTransmissionTimeChannel4.setText(df.format(result));
        }));
        countInTheBundleChannel4.textProperty().addListener(((observableValue, oldValue, newValue) -> {
            double result = calculateAvgTransmission(Double.parseDouble(avgLengthPackageChannel4.getText()), Double.parseDouble(modulationRateChannel4.getText()), Double.parseDouble(countInTheBundleChannel4.getText()));
            avgTransmissionTimeChannel4.setText(df.format(result));
        }));

        avgTransmissionTimeChannel4.textProperty().addListener((observable, oldValue, newValue) -> {
            channels.get(3).setTransmission(Double.parseDouble(newValue));
        });
    }

    private void configureChannel5Tab() {
        probabilityChannel5Failure.setTextFormatter(new StringToDoubleFormatter().getTextFormatter());
        modulationRateChannel5.setTextFormatter(new StringToDoubleFormatter().getTextFormatter());
        countInTheBundleChannel5.setTextFormatter(new StringToDoubleFormatter().getTextFormatter());
        recoveryTimeChannel5.setTextFormatter(new StringToDoubleFormatter().getTextFormatter());
        avgLengthPackageChannel5.setTextFormatter(new StringToDoubleFormatter().getTextFormatter());

        probabilityChannel5Failure.textProperty().addListener((observableValue, oldValue, newValue) -> {
            channels.stream().filter(c->c.getLabel().equals("5")).findFirst().ifPresent(c -> channelFailureChanged(c, newValue));
        });

        modulationRateChannel5.textProperty().addListener(((observableValue, oldValue, newValue) -> {
            double result = calculateAvgTransmission(Double.parseDouble(avgLengthPackageChannel5.getText()), Double.parseDouble(modulationRateChannel5.getText()), Double.parseDouble(countInTheBundleChannel5.getText()));
            avgTransmissionTimeChannel5.setText(df.format(result));
        }));
        avgLengthPackageChannel5.textProperty().addListener(((observableValue, oldValue, newValue) -> {
            double result = calculateAvgTransmission(Double.parseDouble(avgLengthPackageChannel5.getText()), Double.parseDouble(modulationRateChannel5.getText()), Double.parseDouble(countInTheBundleChannel5.getText()));
            avgTransmissionTimeChannel5.setText(df.format(result));
        }));
        countInTheBundleChannel5.textProperty().addListener(((observableValue, oldValue, newValue) -> {
            double result = calculateAvgTransmission(Double.parseDouble(avgLengthPackageChannel5.getText()), Double.parseDouble(modulationRateChannel5.getText()), Double.parseDouble(countInTheBundleChannel5.getText()));
            avgTransmissionTimeChannel5.setText(df.format(result));
        }));

        avgTransmissionTimeChannel5.textProperty().addListener((observable, oldValue, newValue) -> {
            channels.get(4).setTransmission(Double.parseDouble(newValue));
        });
    }

    private void configureChannel6Tab() {
        probabilityChannel6Failure.setTextFormatter(new StringToDoubleFormatter().getTextFormatter());
        modulationRateChannel6.setTextFormatter(new StringToDoubleFormatter().getTextFormatter());
        countInTheBundleChannel6.setTextFormatter(new StringToDoubleFormatter().getTextFormatter());
        recoveryTimeChannel6.setTextFormatter(new StringToDoubleFormatter().getTextFormatter());
        avgLengthPackageChannel6.setTextFormatter(new StringToDoubleFormatter().getTextFormatter());

        probabilityChannel6Failure.textProperty().addListener((observableValue, oldValue, newValue) -> {
            channels.stream().filter(c->c.getLabel().equals("6")).findFirst().ifPresent(c -> channelFailureChanged(c, newValue));
        });

        modulationRateChannel6.textProperty().addListener(((observableValue, oldValue, newValue) -> {
            double result = calculateAvgTransmission(Double.parseDouble(avgLengthPackageChannel6.getText()), Double.parseDouble(modulationRateChannel6.getText()), Double.parseDouble(countInTheBundleChannel6.getText()));
            avgTransmissionTimeChannel6.setText(df.format(result));
        }));
        avgLengthPackageChannel6.textProperty().addListener(((observableValue, oldValue, newValue) -> {
            double result = calculateAvgTransmission(Double.parseDouble(avgLengthPackageChannel6.getText()), Double.parseDouble(modulationRateChannel6.getText()), Double.parseDouble(countInTheBundleChannel6.getText()));
            avgTransmissionTimeChannel6.setText(df.format(result));
        }));
        countInTheBundleChannel6.textProperty().addListener(((observableValue, oldValue, newValue) -> {
            double result = calculateAvgTransmission(Double.parseDouble(avgLengthPackageChannel6.getText()), Double.parseDouble(modulationRateChannel6.getText()), Double.parseDouble(countInTheBundleChannel6.getText()));
            avgTransmissionTimeChannel6.setText(df.format(result));
        }));

        avgTransmissionTimeChannel6.textProperty().addListener((observable, oldValue, newValue) -> {
            channels.get(5).setTransmission(Double.parseDouble(newValue));
        });
    }
    private Route stringToRoute(String strRoute) {
        List<String> nodesStrList = Arrays.asList(strRoute.split("->"));
        List<Node> nodesList = new ArrayList<>();
        List<Channel> channelList = new ArrayList<>();

        for(String node : nodesStrList) {
            nodes.stream().filter(n -> n.getLabel().equals(node)).findFirst().ifPresent(nodesList::add);
            if (nodesList.size()>1) {
                channels.stream().filter(c -> c.getNodes().contains(nodesList.get(nodesList.size() - 1)) && c.getNodes().contains(nodesList.get(nodesList.size()-2)))
                        .findFirst().ifPresent(channelList::add);
            }
        }

        return new Route(nodesList, nodesStrList.size()-1, strRoute, channelList);
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

    private List<Channel> stringToChannels(String strRoute) {
        List<Node> nodeList = stringToNodes(strRoute);
        List<Channel> channelList = new ArrayList<>();

        for(int i = 1; i< nodeList.size(); i++) {
            int finalI = i;
            channels.stream().filter(c -> c.getNodes().contains(nodeList.get(finalI)) && c.getNodes().contains(nodeList.get(finalI - 1)))
                    .findFirst().ifPresent(channelList::add);
        }
        channelList.forEach(c -> System.out.println(c.getLabel()));
        return channelList;
    }

    private void nodeFailureChanged(String labelNode, String value) {
        nodes.get(Integer.parseInt(labelNode) - 1).setProbabilityNodeFailure(Double.valueOf(value));
        List<Route> routeWithNode = routes.stream().filter(r -> r.getRouteStr().contains(labelNode)).toList();

        if(!routeWithNode.isEmpty()) {
            routeWithNode.forEach(this::updateRoute);
        }
    }

    private void channelFailureChanged(Channel channel, String value) {
        channels.stream().filter(c -> c.equals(channel)).findFirst().ifPresent(c -> c.setProbabilityFailure(Double.valueOf(value)));
        List<Route> routeWithNode = routes.stream().filter(r -> r.getChannel().contains(channel)).toList();

        if(!routeWithNode.isEmpty()) {
            routeWithNode.forEach(this::updateRoute);
        }
    }

    private void changeProbabilityOfRoute(String routeStr) {
        Optional<Route> findRoute = routes.stream().filter(r -> r.getRouteStr().equals(routeStr)).findFirst();
        if(findRoute.isPresent()) {
            double probability = findRoute.get().getProbabilityOfRoute();
            routeFailure.setText(df.format(probability));
            normalRouteFailure.setText(df.format(getNormalProbabilityOfRoutes(probability)));
            setIntensity(findRoute.get(), findRoute.get().getRoute().get(1), getNormalProbabilityOfRoutes(probability));
        }

    }

    private void updateRoute(Route routeForUpdate) {
        routes.stream().filter(r -> r.equals(routeForUpdate)).findFirst().ifPresent(r -> {
            r.setRoute(stringToNodes(routeForUpdate.getRouteStr()));
            r.setChannel(stringToChannels(routeForUpdate.getRouteStr()));
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

    private double calculateAvgTransmission(double avgLengthPkg, double modulationRate, double countBundle) {
        if(modulationRate*countBundle != 0) {
            return avgLengthPkg / (modulationRate * countBundle);
        } else return 0.0;
    }

    private void setIntensity(Route route, Node node, double probabilityOrIntensity) {
        double intensity = 0.0;
        System.out.println("setIntensity for node " + node.getLabel());
        if(!intesityMessage.getText().isBlank()) {
            if(route.getRoute().indexOf(node) > 1) {
                System.out.println(intesityMessage.getText() + "*" + probabilityOrIntensity);
                intensity = probabilityOrIntensity;
            } else {
                System.out.println(intesityMessage.getText() + "*" + probabilityOrIntensity);
                intensity = Double.parseDouble(intesityMessage.getText()) * probabilityOrIntensity;
            }
        }
        switch (node.getLabel()) {
            case "1":
                intensityNode1.setText(df.format(intensity));
                break;
            case "2":
                intensityNode2.setText(df.format(intensity));
                break;
            case "3":
                intensityNode3.setText(df.format(intensity));
                break;
            case "4":
                intensityNode4.setText(df.format(intensity));
                break;
        }
        setDelayTime(route, node, intensity);

        if (route.getRoute().size() > 2 && route.getRoute().indexOf(node) == 1) {
            for (int i = 2; i < route.getRoute().size(); i++) {
                System.out.println("set intensity for next node " + route.getRoute().get(i).getLabel());
                setIntensity(route, route.getRoute().get(i), getIntensityForPreviousNode(route, route.getRoute().get(i)));
            }
        }

        setAvgDelayTime();
        setAvgTransferTime(route);
    }
    private void setDelayTime(Route route, Node node, double intensity) {
        int endNode = route.getRoute().indexOf(node);
        double p;
        Optional<Channel> channel = channels.stream().filter(c ->
                c.getNodes().contains(route.getRoute().get(endNode-1)) && c.getNodes().contains(route.getRoute().get(endNode)))
                .findFirst();
        if(channel.isPresent()) {
            Channel chanelFind = channel.get();
            p = (intensity * chanelFind.getTransmission());
            System.out.println("set delay time for node " + node.getLabel());
            System.out.println(intensity + "*" + chanelFind.getTransmission() + "*" + intensity + "/(1 - " + intensity + " * " +chanelFind.getTransmission());
            switch(node.getLabel()) {
                case "1":
                    delayTimeNode1.setText(df.format((p * chanelFind.getTransmission()) / (1.0 - p)));
                    setCountMessage(node, p);
                    break;
                case "2":
                    delayTimeNode2.setText(df.format((p * chanelFind.getTransmission()) / (1.0 - p)));
                    setCountMessage(node,  p);
                    break;
                case "3":
                    delayTimeNode3.setText(df.format((p * chanelFind.getTransmission()) / (1.0 - p)));
                    setCountMessage(node,  p);
                    break;
                case "4":
                    setCountMessage(node, p);
                    delayTimeNode4.setText(df.format((p * chanelFind.getTransmission()) / (1.0 - p)));
                    break;
            }
        }
    }

    private void setCountMessage(Node node, double p) {
        System.out.println("set count message for node " + node.getLabel());
        System.out.println(p + "*" + p + "/(1 - "+p + ")");
        switch (node.getLabel()) {
            case "1":
                countMessageNode1.setText(df.format(p * p/(1 - p)));
                break;
            case "2":
                countMessageNode2.setText(df.format(p * p/(1 - p)));
                break;
            case "3":
                countMessageNode3.setText(df.format(p * p/(1 - p)));
                break;
            case "4":
                countMessageNode4.setText(df.format(p * p/(1 - p)));
                break;
        }
    }

    private double getIntensityForPreviousNode(Route route, Node node) {
        int indexNode = route.getRoute().indexOf(node) - 1;
        return switch (route.getRoute().get(indexNode).getLabel()) {
            case "1" -> {
                System.out.println("previous node: 1");
                yield Double.parseDouble(intensityNode1.getText()) / Double.parseDouble(countMessageNode1.getText());
            }
            case "2" -> {
                System.out.println("previous node: 2");
                yield Double.parseDouble(intensityNode2.getText()) / Double.parseDouble(countMessageNode2.getText());
            }
            case "3" -> {
                System.out.println("previous node: 3" );
                yield Double.parseDouble(intensityNode3.getText()) / Double.parseDouble(countMessageNode3.getText());
            }
            case "4" -> {
                System.out.println("previous node: 4" );
                yield Double.parseDouble(intensityNode4.getText()) / Double.parseDouble(countMessageNode4.getText());
            }
            default -> 0.0;
        };
    }

    private void setAvgDelayTime() {
        double delayTime1 = Double.parseDouble(delayTimeNode1.getText());
        double delayTime2 = Double.parseDouble(delayTimeNode2.getText());
        double delayTime3 = Double.parseDouble(delayTimeNode3.getText());
        double delayTime4 = Double.parseDouble(delayTimeNode4.getText());
        double sum = delayTime1 + delayTime2 + delayTime3 + delayTime4;

        avgDelayTime.setText(df.format(sum));
    }
    private void setAvgTransferTime(Route route) {
        double sum = 0.0;
        List<Channel> channelList = route.getChannel();
        sum += (channelList.stream().mapToDouble(Channel::getTransmission).sum() + Double.parseDouble(avgDelayTime.getText()));
        avgTransferTime.setText(df.format(sum));
    }

    private void clearIntensity(){
        intensityNode1.setText(String.valueOf(0.0));
        intensityNode2.setText(String.valueOf(0.0));
        intensityNode3.setText(String.valueOf(0.0));
        intensityNode4.setText(String.valueOf(0.0));
    }

    private void clearDelayTime() {
        delayTimeNode1.setText(String.valueOf(0.0));
        delayTimeNode2.setText(String.valueOf(0.0));
        delayTimeNode3.setText(String.valueOf(0.0));
        delayTimeNode4.setText(String.valueOf(0.0));
    }
    private void clearCountMessage() {
        countMessageNode1.setText(String.valueOf(0.0));
        countMessageNode2.setText(String.valueOf(0.0));
        countMessageNode3.setText(String.valueOf(0.0));
        countMessageNode4.setText(String.valueOf(0.0));
    }
}
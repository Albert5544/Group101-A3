package ca.ubc.cs304.model;

public class VehicleTypeModel {
    private final String vtname;
    private final String features;
    private final double wrate;
    private final double drate;
    private final double hrate;
    private final double wirate;
    private final double dirate;
    private final double hirate;
    private final double krate;

    public VehicleTypeModel(String vtname, String features, double hrate, double drate, double wrate, double hirate, double dirate, double wirate, double krate) {
        this.vtname = vtname;
        this.features = features;
        this.wrate = wrate;
        this.drate = drate;
        this.hrate = hrate;
        this.wirate = wirate;
        this.dirate = dirate;
        this.hirate = hirate;
        this.krate = krate;
    }

    public String getVtname() {
        return vtname;
    }

    public String getFeatures() {
        return features;
    }

    public double getHrate() {
        return hrate;
    }

    public double getDrate() {
        return drate;
    }

    public double getWrate() {
        return wrate;
    }

    public double getHirate() {
        return hirate;
    }

    public double getDirate() {
        return dirate;
    }

    public double getWirate() {
        return wirate;
    }

    public double getKrate() {
        return krate;
    }

}


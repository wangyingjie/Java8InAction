package lambdasinaction.optionals.refacter;


import java.util.Optional;

public class Mobile {

    private long id;
    private String brand;
    private String name;
    private Optional<DisplayFeatures> displayFeatures;
    // Like wise we can see MemoryFeatures, CameraFeatures etc.

    public Mobile(long id, String brand, String name, Optional<DisplayFeatures> displayFeatures) {
        this.id = id;
        this.brand = brand;
        this.name = name;
        this.displayFeatures = displayFeatures;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Optional<DisplayFeatures> getDisplayFeatures() {
        return displayFeatures;
    }

    public void setDisplayFeatures(Optional<DisplayFeatures> displayFeatures) {
        this.displayFeatures = displayFeatures;
    }


    public int getMobileScreenWidth(Optional<Mobile> mobile) {
//        if(mobile != null){
//            DisplayFeatures dfeatures = mobile.getDisplayFeatures();
//            if(dfeatures != null){
//                ScreenResolution resolution = dfeatures.getResolution();
//                if(resolution != null){
//                    return resolution.getWidth();
//                }
//            }
//        }

        //mobile.get().getDisplayFeatures().flatMap(m -> m.getResolution()).get().getHeight();
        // return 0;

        return mobile.flatMap(Mobile::getDisplayFeatures)
                .flatMap(DisplayFeatures::getResolution)
                .map(ScreenResolution::getHeight)
                .orElse(1);
    }

}

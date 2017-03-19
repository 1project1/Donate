package app.project.donate.ngolocator;


public class GIS {

    public static final int SIZE = 10;
    public static final PointFloating TESTS[] = new PointFloating[SIZE];


    static PointFloating[] latlongT = new PointFloating[SIZE];

    /*Dummy Values*/
    public  void initTests() {
        for (int i = 0; i < SIZE; i++) {
            TESTS[i] = new PointFloating();
        }
        TESTS[0].setXY(28.4793898, 77.3029815);//crown Intz
        TESTS[1].setXY(29.9613391, 76.8011094);//near nit kuruk
        TESTS[2].setXY(28.6304529, 77.3699043);//near JIIT
        TESTS[3].setXY(28.8820417, 76.6201383);//near MDU
        TESTS[4].setXY(28.409308, 77.4009763);// near Lingyas
        TESTS[6].setXY(28.5450013, 77.188334); //near IIT D
        TESTS[7].setXY(28.3639812, 75.5847897); //near bits
        TESTS[8].setXY(12.9713946, 79.1530457); //near vit
        TESTS[9].setXY(15.4225271, 73.9776748);//near iit goa
    }

     void init() {
        for (int i = 0; i < SIZE; i++) latlongT[i] = new PointFloating();
         // Distt. Red Cross Society, Red Cross Bhawan, Sec-12, Faridabad- Ph: 2283176
                latlongT[0].setXY(28.386993, 77.297040, "Distt. Red Cross Society");
         // Distt. Child Welfare Council, Bal Bhawan, NIT Faridabad
                latlongT[1].setXY(28.387570, 77.300378, "Distt. Child Welfare Council");
         // Saint Mary Church, Sec-9, Faridabad
                latlongT[2].setXY(28.378118, 77.333748, "Saint Mary Church");
         // Lion Club, 115, Sector-10, Faridabad
                latlongT[3].setXY(28.376900, 77.325746, "Lion Club");
         // Plot No-3, Near Sector 37 Police Station, Faridabad - 121002
                latlongT[4].setXY(28.480521, 77.329061, "Lala Diwan Chand Trust");
         // Rotary Club Of Faridabad, B-321-323, 2nd Floor, Nehru Ground, NIT Faridabad - 121001
                latlongT[5].setXY(28.391532, 77.305287, "Rotary Club Of Faridabad");
         // National Association For The Blind, Haryana State Branch, Central Green, K.C. Road N.I.T. Faridabad 121001
                latlongT[6].setXY(28.395879, 77.300690, "National Association For The Blind, Haryana State Branch");
        latlongT[7].setXY(28.3639812, 75.5847897, "BITS Pilani");//BITS
        latlongT[8].setXY(12.9713946, 79.1530457, "VIT Vellore");//VIT
        latlongT[9].setXY(15.4225771, 73.9776508, "IIT Goa");//IIT Goa
    }


    public String getNgoLocation(double X, double Y){
        String s = "";
        init();
        initTests();
        PointFloating[] filtered = new PointFloating[SIZE];
        int radius = 10;
        radius *= 1000;
        int pos = 0;
        /*double X = TESTS[pos].getX();
        double Y = TESTS[pos].getY();*/

        PointFloating center = new PointFloating(X, Y);

        final double mult = 1.1;
        PointFloating p1 = AlgorithmGIS.derivePos(center, mult * radius, 0);
        PointFloating p2 = AlgorithmGIS.derivePos(center, mult * radius, 90);
        PointFloating p3 = AlgorithmGIS.derivePos(center, mult * radius, 180);
        PointFloating p4 = AlgorithmGIS.derivePos(center, mult * radius, 270);
        int c = 0;

        /* System.out.println("p1:" + p1.getX() + "\t" + p1.getY() + "\n" +
                "p2:" + p2.getX() + "\t" + p2.getY() + "\n" +
                "p3:" + p3.getX() + "\t" + p3.getY() + "\n" +
                "p4:" + p4.getX() + "\t" + p4.getY()
        );*/
        for (int i = 0; i < SIZE; i++) {
            if (latlongT[i].getX() > p3.getX() && latlongT[i].getX() < p1.getX()
                    && latlongT[i].getY() < p2.getY() && latlongT[i].getY() > p4.getY()) {
                filtered[c] = new PointFloating();
                filtered[c].setXY(latlongT[i].getX(), latlongT[i].getY(), latlongT[i].getName());
                c++;
            }
        }

        double min = 999999;
        for (int i = 0; i < c; i++) {
            if (AlgorithmGIS.pointIsInCircle(filtered[i], center, radius)) {
                //filtered[i].printAll();
                if(AlgorithmGIS.getDistanceBetweenTwoPoints(filtered[i],center) < min) {
                    min = AlgorithmGIS.getDistanceBetweenTwoPoints(filtered[i],center);
                    s = filtered[i].getName();
                }
            }
        }

        return s;
    }

}

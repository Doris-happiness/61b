import synthesizer.GuitarString;

public class GuitarHero {
    private static final double CONCERT_A = 440.0;

    public static void main(String[] args) {
        /* create guitar strings for concert A */
        String keyborad = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        GuitarString[] guitarStr = new GuitarString[37];
        for (int i = 0; i < 37; i += 1) {
            double frequency = CONCERT_A * Math.pow(2, (i - 24.0) / 12.0);
            guitarStr[i] = new GuitarString(frequency);
        }

        while(true) {
            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int index = keyborad.indexOf(key);
                if (index != -1) {
                    guitarStr[index].pluck();
                }
            }

            /* compute the superposition of samples */
            double newSample = 0.0;
            for (GuitarString s : guitarStr) {
                newSample += s.sample();
            }

            // play the sample on standard audio
            StdAudio.play(newSample);

            /* advance the simulation of each guitar string by one step */
            for (GuitarString s : guitarStr) {
                s.tic();
            }
        }
    }

}

package Levels;

/**
 * Created by AHMED ESSAM on 5/14/2017.
 */
public class LevelState implements State {

        private static int state;
        private static int type;
        public  int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public LevelState() {
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

    }



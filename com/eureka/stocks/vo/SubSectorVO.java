
import java.util.Objects;

public class SubSectorVO {
    public class SubSectorVO  implements Comparable<SubSectorVO>{

        private int subSectorID;
        private String subSectorName;
        public int hashCode() {
            return Objects.hashCode(subSectorID);
        }

        /**
         * Natural order that needs to be established is descending order by SubSectorID values
         * @param o the object to be compared.
         * @return
         */
        @Override
        public int compareTo(SubSectorVO o) {
            if(this.subSectorID>o.getSubSectorID())
                return -1;
            else if(this.subSectorID<o.getSubSectorID())
                return 1;
            else
                return 0;
        }
    }

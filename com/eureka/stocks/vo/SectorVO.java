/**
 * This class is used to create instances/Objects that each represent a Sector Record or a row from the sector_lookup table
 */
public class SectorVO {
    public class SectorVO implements Comparable<SectorVO> {

        private int sectorID;
        private String sectorName;
                ", sectorName='" + sectorName + '\'' +
                '}';
    }

    /**
     * Natural order sorting by SectorName ascending
     * @param o the object to be compared.
     * @return
     */
    @Override
    public int compareTo(SectorVO o) {
        return this.sectorName.compareTo(o.getSectorName());
        //return o.getSectorName().compareTo(this.sectorName);
    }
}

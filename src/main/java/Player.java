public class Player {
    private String name;
    private String team;
    private int value;

    public Player(String name, String team, int value){
        this.name = name;
        this.team = team;
        this.value = value;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;
        Player that = (Player) obj;
            if(!this.name.equals(that.name))
                return false;
            if(!this.team.equals(that.team))
                return false;
        return this.value == that.value;
    }
}

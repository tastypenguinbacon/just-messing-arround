package tastypenguinbacon.dontforgetboutit.remember;

/**
 * Created by pingwin on 31.12.17.
 */
public class RememberedIdentifier {
    private String identifier;
    private String name;

    public RememberedIdentifier(String identifier, String name) {
        this.identifier = identifier;
        this.name = name;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getName() {
        return name;
    }
}

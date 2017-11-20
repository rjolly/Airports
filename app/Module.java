import com.google.inject.AbstractModule;
import services.Airports;
import services.Main;

public class Module extends AbstractModule {
    @Override
    public void configure() {
        bind(Airports.class).to(Main.class);
    }
}

package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.service.OwnerService;
import guru.springframework.sfgpetclinic.service.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    public DataInitializer(OwnerService ownerService, VetService vetService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
    }

    @Override
    public void run(String... args) throws Exception {

        Owner owner1 = new Owner();
        owner1.setFirstName("Michael");
        owner1.setLastName("Quinter");
        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("John");
        owner2.setLastName("Doe");
        ownerService.save(owner2);

        System.out.println("----- Loaded Owners -----");

        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Maxwell");
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Vetty");
        vet2.setLastName("McVett");
        vetService.save(vet2);

        System.out.println("----- Loaded Vets -----");
    }
}
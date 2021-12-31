package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.*;
import guru.springframework.sfgpetclinic.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataInitializer implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialtyService specialtyService;
    private final VisitService visitService;

    public DataInitializer(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialtyService specialtyService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {

        int count = petTypeService.findAll().size();
        if (count == 0) {
            loadData();
        }

    }

    private void loadData() {

        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Specialty radiology = new Specialty();
        radiology.setDescription("Radiology");
        Specialty savedRadiology = specialtyService.save(radiology);

        Specialty surgery = new Specialty();
        radiology.setDescription("Surgery");
        Specialty savedSurgery = specialtyService.save(surgery);

        Specialty dentistry = new Specialty();
        radiology.setDescription("Dentistry");
        Specialty savedDentistry = specialtyService.save(dentistry);

        // -------------------------------------------------------------------------------------------------------------

        Owner owner1 = new Owner();
        owner1.setFirstName("Michael");
        owner1.setLastName("Quinter");
        owner1.setAddress("123 Brickerel");
        owner1.setCity("Miami");
        owner1.setTelephone("1234569999");

        Pet mikesPet = new Pet();
        mikesPet.setName("Rosco");
        mikesPet.setPetType(savedDogPetType);
        mikesPet.setOwner(owner1);
        mikesPet.setBirthDate(LocalDate.now());
        owner1.getPets().add(mikesPet);

        ownerService.save(owner1);

        // -------------------------------------------------------------------------------------------------------------

        Owner owner2 = new Owner();
        owner2.setFirstName("John");
        owner2.setLastName("Doe");
        owner2.setAddress("689 Riverside Dr");
        owner2.setCity("Memphis");
        owner2.setTelephone("9019718734");

        Pet johnsPet = new Pet();
        johnsPet.setName("Miss Kitty");
        johnsPet.setPetType(savedCatPetType);
        johnsPet.setOwner(owner2);
        johnsPet.setBirthDate(LocalDate.now());
        owner2.getPets().add(johnsPet);

        ownerService.save(owner2);

        Visit catVisit = new Visit();
        catVisit.setDescription("Sneezy Kitty");
        catVisit.setPet(johnsPet);
        catVisit.setDate(LocalDate.now());

        visitService.save(catVisit);

        System.out.println("----- Loaded Owners -----");

        // -------------------------------------------------------------------------------------------------------------

        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Maxwell");
        vet1.getSpecialities().add(savedRadiology);
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Vetty");
        vet2.setLastName("McVett");
        vet2.getSpecialities().add(savedSurgery);
        vetService.save(vet2);

        System.out.println("----- Loaded Vets -----");
    }
}

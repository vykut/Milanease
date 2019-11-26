package com.example.milanease.data;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.example.milanease.R;
import com.example.milanease.core.ui.Bills.Bill;
import com.example.milanease.core.ui.dashboard.Utility;
import com.example.milanease.data.model.Contract;
import com.example.milanease.data.model.Message;
import com.example.milanease.data.model.Provider;

import java.util.ArrayList;
import java.util.List;

public class RepositoryManager {
    private static final RepositoryManager ourInstance = new RepositoryManager();

    public static RepositoryManager getInstance() {
        return ourInstance;
    }

    private MutableLiveData<List<Provider>> mProviders = new MutableLiveData<>();
    private MutableLiveData<List<Bill>> mBills = new MutableLiveData<>();
    private MutableLiveData<List<Message>> mMessages = new MutableLiveData<>();

    private RepositoryManager() {
        initProviders();
        initBills();
        initMessages();
    }

    public LiveData<List<Bill>> getBills() {
        return mBills;
    }

    public LiveData<List<Bill>> getSegmentedBills(final Utility utility) {
        return Transformations.map(mBills, new Function<List<Bill>, List<Bill>>() {
            @Override
            public List<Bill> apply(List<Bill> input) {
                List<Bill> bills = new ArrayList<>();
                for (Bill bill : input) {
                    if (bill.getUtility().equals(utility))
                        bills.add(bill);
                }
                return bills;
            }
        });
    }

    public LiveData<List<Provider>> getProviders() {
        return mProviders;
    }

    public LiveData<List<Message>> getMessages() {
        return mMessages;
    }

    public void addMessages(List<Message> messages) {
        List<Message> currentMessages = mMessages.getValue();
        currentMessages.addAll(messages);

        mMessages.setValue(currentMessages);
    }

    public void addMessage(Message message) {
        List<Message> currentMessages = mMessages.getValue();
        currentMessages.add(message);
        mMessages.setValue(currentMessages);
    }

    public void addProvider(Provider provider) {
        List<Provider> providers = mProviders.getValue();
        providers.add(provider);
        mProviders.setValue(providers);
    }

    public void addBill(Bill bill) {
        List<Bill> bills = mBills.getValue();
        bills.add(bill);
        mBills.postValue(bills);
    }

    public void addContract(Provider provider, Contract contract) {
        //to be implemented

    }

    private void initBills() {
        List<Bill> bills = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            bills.add(new Bill(Utility.water));
        }

        bills.add(new Bill(Utility.electricity));
        bills.add(new Bill(Utility.electricity));
        bills.add(new Bill(Utility.gas));
        bills.add(new Bill(Utility.gas));

        mBills.setValue(bills);
    }

    // to add contracts
    private void initProviders() {
        List<Provider> providers = new ArrayList<>();

        List<Utility> eUtilities = new ArrayList<>();
        eUtilities.add(Utility.electricity);
        eUtilities.add(Utility.gas);
        Provider enel = new Provider("0001", "Enel", eUtilities, R.drawable.ic_enel_logo_large, R.drawable.ic_enel_logo_small, "080070809", "În România, Grupul Enel deserveşte 2,8 milioane de clienţi prin reţeaua sa de furnizare şi distribuţie, iar Enel Green Power deţine şi operează centrale de producţie a energiei din surse regenerabile.", "contacteem.ro@enel.com", null);

        Provider eon = new Provider("0002", "E.ON", eUtilities, R.drawable.ic_eon_logo_large, R.drawable.ic_eon_logo_small, "0800800928", "E.ON a intrat în România în anul 2005, când a preluat fostele societăţi de stat din domeniul gazelor naturale şi energiei electrice. Companiile din structura grupului E.ON operează, în prezent, o reţea de distribuţie a gazelor naturale în lungime de peste 21.650 km, respectiv o reţea de distribuţie a energiei electrice de peste 81.500 de kilometri şi desfăşoară activităţi în 20 de judeţe situate în jumătatea de Nord a ţării, având circa 3,1 milioane de clienţi. În ultimii 11 ani, E.ON a investit în România peste 1,4 miliarde de euro.", "serviciiclienti@eon-romania.ro", null);

        Provider electricaFurnizare = new Provider("0003", "Electrica Furnizare", eUtilities, R.drawable.ef_logo_large, R.drawable.ef_logo_small, "0800800048", "ELECTRICA FURNIZARE SA, lider pe piața de energie din România și furnizorul cu cel mai mare portofoliu de clienți, se remarcă printr-o bună cunoaştere a caracteristicilor regionale de consum, o infrastructură modernă şi specialişti pregătiţi să ofere servicii de calitate clienţilor.", "office@electrica.ro", null);

        Provider engie = new Provider("0004", "Engie", eUtilities, R.drawable.ic_engie_logo_large, R.drawable.engie_logo_small, "0800877778", "ENGIE este un grup mondial activ în domeniul energiei şi al serviciilor conexe ce îşi fondează strategia pe o creştere responsabilă a activităţilor, menită să răspundă provocărilor majore ale tranziţiei energetice. Având drept ambiție să contribuim la realizarea unui progres armonios, ne propunem să răspundem celor mai importante provocări mondiale cum ar fi lupta împotriva încălzirii globale, accesul tuturor la energie, garantarea siguranţei în aprovizionare şi optimizarea utilizării resurselor. Din această perspectivă le propunem clienților noştri -persoane fizice, juridice şi colectivităţilor locale- soluţii de energie performante şi inovatoare.\n" + " Ambitia noastră este transpusă de cei 150.000 de angajați din cele 70 de țări unde suntem prezenți.", "consiliere-clienti@ro.engie.com", null);

        List<Utility> wUtilities = new ArrayList<>();
        wUtilities.add(Utility.water);
        Provider apaNova = new Provider("0005", "Apa Nova", wUtilities, R.drawable.ic_apanova_logo_large, R.drawable.apanova_logo_small, "0212077777", "Existăm pentru ca bucureștenii să primească apă curată și bună de băut acasă sau în fabricile în care au nevoie de ea. Apoi, tot noi ne asigurăm că apa se întoarce în natură curată și sigură pentru mediul înconjurător. Peste 1.800 de profesioniști lucrează zilnic, dedicați unei comunități cu 2 milioane de suflete. Suntem parte din marea familie Veolia, un grup internațional cu peste 150 de ani de experiență în administrarea rețelelor urbane de apă și canalizare. Am ajuns protectori ai apei din București după ce Apa Nova și Municipiul București au semnat contractul de concesiune pentru o perioadă de 25 de ani. Atunci am primit dreptul și responsabilitatea de a pune la punct un sistem public modern și demn de o capitală europeană.", "relatii.clienti@ apanovabucuresti.ro", null);

        providers.add(enel);
        providers.add(eon);
        providers.add(electricaFurnizare);
        providers.add(engie);
        providers.add(apaNova);

        mProviders.setValue(providers);
    }

    private void initMessages() {
        List<Message> messages = new ArrayList<>();
        mMessages.setValue(messages);
    }
}

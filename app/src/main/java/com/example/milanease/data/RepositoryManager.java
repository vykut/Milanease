package com.example.milanease.data;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.milanease.R;
import com.example.milanease.data.model.Bill;
import com.example.milanease.core.ui.dashboard.Utility;
import com.example.milanease.data.model.Contract;
import com.example.milanease.data.model.DashboardModel;
import com.example.milanease.data.model.Message;
import com.example.milanease.data.model.Provider;
import com.example.milanease.data.remote.NetworkManager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepositoryManager {
    private static final RepositoryManager ourInstance = new RepositoryManager();

    public static RepositoryManager getInstance() {
        return ourInstance;
    }

    private MutableLiveData<List<Provider>> mProviders = new MutableLiveData<>();
    private MutableLiveData<List<Bill>> mBills = new MutableLiveData<>();
    private MutableLiveData<List<Message>> mMessages = new MutableLiveData<>();
    private MutableLiveData<List<DashboardModel>> mDashboardModels = new MutableLiveData<>();

    private RepositoryManager() {
        initProviders();
        initBills();
        initMessages();
    }

    public void fetchDashboardModel() {
        if (mDashboardModels.getValue() == null)
        NetworkManager.getInstance().getDashboardModel().enqueue(new Callback<List<DashboardModel>>() {
           @Override
           public void onResponse(Call<List<DashboardModel>> call, Response<List<DashboardModel>> response) {
               if (response.isSuccessful())
               mDashboardModels.setValue(response.body());
           }

           @Override
           public void onFailure(Call<List<DashboardModel>> call, Throwable t) {
               Log.e("tag", t.getLocalizedMessage());
//               mDashboardModels.setValue(null);
           }
       });
    }

    public LiveData<List<DashboardModel>> getDashboardModel() {
        return mDashboardModels;
    }

    public LiveData<List<Bill>> getBills() {
        return mBills;
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

        Calendar billDate = Calendar.getInstance();

        List<Bill> waterBills = new ArrayList<>();
        List<Bill> electricityBills = new ArrayList<>();
        List<Bill> gasBills = new ArrayList<>();

        billDate.set(Calendar.MONTH, 7);
        electricityBills.add(new Bill(Utility.electricity, "0001", 17.37, (Calendar) billDate.clone(), 164));
        billDate.set(Calendar.MONTH, 8);
        electricityBills.add(new Bill(Utility.electricity, "0001", 18.54, (Calendar) billDate.clone(), 175));
        waterBills.add(new Bill(Utility.water, "0005", 10.23, (Calendar) billDate.clone(), 8.89));
        gasBills.add(new Bill(Utility.gas, "0001", 23.08, (Calendar) billDate.clone(), 81));
        billDate.set(Calendar.MONTH, 9);
        gasBills.add(new Bill(Utility.gas, "0001", 48.42, (Calendar) billDate.clone(), 170));
        electricityBills.add(new Bill(Utility.electricity, "0001", 19.26, (Calendar) billDate.clone(), 182));
        waterBills.add(new Bill(Utility.water, "0005", 13.72, (Calendar) billDate.clone(), 11.93));
        billDate.set(Calendar.MONTH, 10);
        gasBills.add(new Bill(Utility.gas, "0001", 71.25, (Calendar) billDate.clone(), 250));
        electricityBills.add(new Bill(Utility.electricity, "0001", 20.71, (Calendar) billDate.clone(), 196));
        waterBills.add(new Bill(Utility.water, "0005", 26.49, (Calendar) billDate.clone(), 23.04));

        bills.addAll(waterBills);
        bills.addAll(gasBills);
        bills.addAll(electricityBills);

        Collections.sort(bills, Collections.<Bill>reverseOrder());

        mBills.setValue(bills);
    }

    // to add contracts
    private void initProviders() {
        List<Provider> providers = new ArrayList<>();

        String userID = LoginRepository.getInstance(new LoginDataSource()).getUser().getClientID();
        String userName = LoginRepository.getInstance(new LoginDataSource()).getUser().getDisplayName();
//        String address = LoginRepository.getInstance(new LoginDataSource()).getUser().getAddress();
        Contract enelContract = new Contract("Enel Simplu Anual", "0001", userID, userName/*, address*/);
        Contract apaNovaContract = new Contract("Apa Nova furnizare apa potabila", "0005", userID, userName/*, address*/);

        List<Utility> eUtilities = new ArrayList<>();
        eUtilities.add(Utility.electricity);
        eUtilities.add(Utility.gas);
        Provider enel = new Provider(1, "Enel", eUtilities, R.drawable.ic_enel_logo_large, R.drawable.ic_enel_logo_small, "080070809", "În România, Grupul Enel deserveşte 2,8 milioane de clienţi prin reţeaua sa de furnizare şi distribuţie, iar Enel Green Power deţine şi operează centrale de producţie a energiei din surse regenerabile.", "contacteem.ro@enel.com", enelContract);
        Provider engie = new Provider(4, "Engie", eUtilities, R.drawable.ic_engie_logo_large, R.drawable.engie_logo_small, "0800877778", "ENGIE este un grup mondial activ în domeniul energiei şi al serviciilor conexe ce îşi fondează strategia pe o creştere responsabilă a activităţilor, menită să răspundă provocărilor majore ale tranziţiei energetice. Având drept ambiție să contribuim la realizarea unui progres armonios, ne propunem să răspundem celor mai importante provocări mondiale cum ar fi lupta împotriva încălzirii globale, accesul tuturor la energie, garantarea siguranţei în aprovizionare şi optimizarea utilizării resurselor. Din această perspectivă le propunem clienților noştri -persoane fizice, juridice şi colectivităţilor locale - soluţii de energie performante şi inovatoare.\n" + "Ambitia noastră este transpusă de cei 150.000 de angajați din cele 70 de țări unde suntem prezenți.", "consiliere-clienti@ro.engie.com", null);
        Provider electricaFurnizare = new Provider(3, "Electrica Furnizare", eUtilities, R.drawable.ef_logo_large, R.drawable.ef_logo_small, "0800800048", "ELECTRICA FURNIZARE SA, lider pe piața de energie din România și furnizorul cu cel mai mare portofoliu de clienți, se remarcă printr-o bună cunoaştere a caracteristicilor regionale de consum, o infrastructură modernă şi specialişti pregătiţi să ofere servicii de calitate clienţilor.", "office@electrica.ro", null);


        List<Utility> wUtilities = new ArrayList<>();
        wUtilities.add(Utility.water);
        Provider apaNova = new Provider(5, "Apa Nova", wUtilities, R.drawable.ic_apanova_logo_large, R.drawable.apanova_logo_small, "0212077777", "Existăm pentru ca bucureștenii să primească apă curată și bună de băut acasă sau în fabricile în care au nevoie de ea. Apoi, tot noi ne asigurăm că apa se întoarce în natură curată și sigură pentru mediul înconjurător. Peste 1.800 de profesioniști lucrează zilnic, dedicați unei comunități cu 2 milioane de suflete. Suntem parte din marea familie Veolia, un grup internațional cu peste 150 de ani de experiență în administrarea rețelelor urbane de apă și canalizare. Am ajuns protectori ai apei din București după ce Apa Nova și Municipiul București au semnat contractul de concesiune pentru o perioadă de 25 de ani. Atunci am primit dreptul și responsabilitatea de a pune la punct un sistem public modern și demn de o capitală europeană.", "relatii.clienti@ apanovabucuresti.ro", apaNovaContract);

        List<Utility> gUtilities = new ArrayList<>();
        gUtilities.add(Utility.gas);
        Provider eon = new Provider(2, "E.ON", gUtilities, R.drawable.ic_eon_logo_large, R.drawable.ic_eon_logo_small, "0800800928", "E.ON a intrat în România în anul 2005, când a preluat fostele societăţi de stat din domeniul gazelor naturale şi energiei electrice. Companiile din structura grupului E.ON operează, în prezent, o reţea de distribuţie a gazelor naturale în lungime de peste 21.650 km, respectiv o reţea de distribuţie a energiei electrice de peste 81.500 de kilometri şi desfăşoară activităţi în 20 de judeţe situate în jumătatea de Nord a ţării, având circa 3,1 milioane de clienţi. În ultimii 11 ani, E.ON a investit în România peste 1,4 miliarde de euro.", "serviciiclienti@eon-romania.ro", null);

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

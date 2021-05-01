package com.programming.techie;

import com.sun.source.doctree.SystemPropertyTree;
import jdk.jfr.Enabled;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import java.lang.String;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ContactManagerTest {

    ContactManager contactManager;

    @BeforeAll
    public static void setupAll(){
        System.out.println("Should print before all tests.");
    }
    @BeforeEach
    public void setup(){
        contactManager=new ContactManager();
    }
    @Test
    public void shouldCreateContact(){
        contactManager.addContact("Chauka","Dinuwan","0234567897");
        Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
        Assertions.assertEquals(1,contactManager.getAllContacts().size());
    }



    @Test
    @ParameterizedTest
    @Disabled
    @MethodSource("phoneNumberList()")
    public void shouldCreateContactUsingValueSource(String phoneNumber){
        contactManager.addContact("Chauka","Dinuwan",phoneNumber);
        Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
        Assertions.assertEquals(1,contactManager.getAllContacts().size());
    }
    private static List<String> phoneNumberList(){
        return Arrays.asList("0123456789","01234567865","0321654987");
    }
    @Test
    @ParameterizedTest
    @Disabled
    @CsvSource({"0123456789","0789645632","0123456789"})
    public void shouldCreateContactUsingCsvSource(String phoneNumber){
        contactManager.addContact("Chauka","Dinuwan",phoneNumber);
        Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
        Assertions.assertEquals(1,contactManager.getAllContacts().size());
    }

}
@Nested
class RepeatedTst{

    ContactManager contactManager;

    @Test
    @RepeatedTest(value=5,
            name="Repeating Contact Creation Test {currentRepetition}of{totalRepetitions}")
    public void shouldCreateContactOnRepeated(){
        contactManager.addContact("Chauka","Dinuwan","0234567897");
        Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
        Assertions.assertEquals(1,contactManager.getAllContacts().size());
    }
}
@Nested
class NotRepeated{

    ContactManager contactManager;

    @Test
    @DisplayName("Should Not Create Contact When Name is Null")
    public void shouldThrowRuntimeExceptionFirstNameIsNull(){
        Assertions.assertThrows(RuntimeException.class,()->{
            contactManager.addContact(null,"Doe","0467115744");
        });
    }

    @Test
    @DisplayName("Should Not Create Contact When LastName is null")
    public void shouldThrowRuntimeExceptionWhenLastNameIsNull(){
        Assertions.assertThrows(RuntimeException.class,()->{
            contactManager.addContact("jhone",null,"0767115744");
        });
    }

    @Test
    @EnabledOnOs(value= OS.MAC,disabledReason = "Enable only an Mac Os")
    public void shouldCreateContactOnMac(){
        contactManager.addContact("Chauka","Dinuwan","0234567897");
        Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
        Assertions.assertEquals(1,contactManager.getAllContacts().size());
    }

    @Test
    @DisplayName("Contact creation on Developer Machine")
    public void shouldCreateContactOnDev(){
        Assumptions.assumeTrue("DEV".equals(System.getProperty("ENV")));
        contactManager.addContact("Chanuka","Dinuwan","0234567897");
        Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
        Assertions.assertEquals(1,contactManager.getAllContacts().size());
    }
}
package com.example.insurance.RepositoryTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
 
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
 
import com.example.insurance.entity.Beneficiary;
import com.example.insurance.entity.Policy;
import com.example.insurance.entity.User;
import com.example.insurance.repository.BeneficiaryRepository;
import com.example.insurance.repository.PolicyRepository;
import com.example.insurance.repository.UserRepository;

import org.junit.jupiter.api.Assertions;
 
 
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BeneficiaryRepoTest {
	
	@Autowired
	BeneficiaryRepository beneficiaryRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PolicyRepository policyRepository;
	
	public User getDummyUser() 
	{
		User user=new User();
		user.setUserName("Eswar_2244");
		user.setPassword("Eswar@1234");
		user.setEmail("tvaeswar@gmail.com");
		user.setFirstName("Eswar");
		user.setLastName("Tummalapalli");
		user.setDateOfBirth(LocalDate.of(2002,9,28));
		user.setAddress("West Godavari");
		user.setCity("Tadepalligudem");
		user.setState("Andhra Pradesh");
		user.setZipCode("534197");
		
		return user;
	}
	
	public Policy getDummyPolicy()
	{
		Policy policy=new Policy();
		
		policy.setPolicyNumber(76431801);
		policy.setCoverageAmount(150000);
		policy.setDuration(12);
		policy.setStartDate(LocalDate.of(2024,4,17));
		policy.setEndDate(LocalDate.of(2025,4,17));
		policy.setPremium(15000);
		
		return policy;
	}
	
	public Beneficiary getDummyBeneficiary() 
	{
		Beneficiary beneficiary = new Beneficiary();
		
		beneficiary.setUser(getDummyUser());
		beneficiary.setPolicies(getDummyPolicy());
		beneficiary.setName("Vamsi");
		beneficiary.setRelationship("Brother");
		
		return beneficiary;
	}
	
	@Test
    void findAll_should_return_beneficiary_list() {
		//When
        List<Beneficiary> beneficiaries = this.beneficiaryRepository.findAll();
        //Then
        Assertions.assertEquals(6,beneficiaries.size());
    }
	
	@Test
    void findById_should_return_beneficiary() {
        Optional<Beneficiary> beneficiary = this.beneficiaryRepository.findById(4);
        Assertions.assertTrue(beneficiary.isPresent());
    }
	
	
	@Test
    void save_should_insert_new_beneficiary() {
		
		//Given
        Beneficiary b =getDummyBeneficiary();
        //When
        Beneficiary newBeneficiary=beneficiaryRepository.save(b);
        //Then
        Assertions.assertNotNull(newBeneficiary);
        Assertions.assertEquals("Vamsi", newBeneficiary.getName());
        Assertions.assertEquals("Brother", newBeneficiary.getRelationship());
        
    }
	
	@Test
    void save_should_update_existing_beneficiary() 
	{
		//Given
        Beneficiary existingBeneficiary = new Beneficiary();
        existingBeneficiary.setBeneficiaryId(5);
        existingBeneficiary.setName("Siri");
        existingBeneficiary.setRelationship("Sister");
        // When
        Beneficiary updatedBeneficiary = this.beneficiaryRepository.save(existingBeneficiary);
        //Then
        Assertions.assertNotNull(updatedBeneficiary);
        Assertions.assertEquals("Siri", updatedBeneficiary.getName());
    }
	
	@Test
    void deleteById_should_delete_beneficiary() 
	{
		//When
        this.beneficiaryRepository.deleteById(1);
        Optional<Beneficiary> beneficiary = this.beneficiaryRepository.findById(1);
        //Then
        Assertions.assertFalse(beneficiary.isPresent());
    }
}


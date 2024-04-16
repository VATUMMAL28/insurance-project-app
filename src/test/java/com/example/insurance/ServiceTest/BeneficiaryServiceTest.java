package com.example.insurance.ServiceTest;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
 
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.insurance.dto.BeneficiaryDTO;
import com.example.insurance.entity.Beneficiary;
import com.example.insurance.entity.Policy;
import com.example.insurance.entity.User;
import com.example.insurance.mapper.BeneficiaryMapper;
import com.example.insurance.repository.BeneficiaryRepository;
import com.example.insurance.repository.PolicyRepository;
import com.example.insurance.repository.UserRepository;
import com.example.insurance.service.BeneficiaryServiceImpl;

//You can apply the extension by adding @ExtendWith(MockitoExtension.class) to the test class and annotating mocked fields with @Mock
@ExtendWith(MockitoExtension.class)
public class BeneficiaryServiceTest {
	
	//@Mock allows us to create and inject a mock of it
	@Mock
	BeneficiaryRepository beneficiaryRepository;
	@Mock
    UserRepository userRepository;
	@Mock
    PolicyRepository policyRepository;
	@Mock
    BeneficiaryMapper beneficiaryMapper;
	@Mock
	Beneficiary beneficiary;
	@BeforeEach
    void setUp() {
        Mockito.lenient().when(beneficiaryRepository.findById(2)).thenReturn(Optional.of(beneficiary));
    }

	
	@InjectMocks
	private BeneficiaryServiceImpl beneficiaryService;
	
	public User getDummyUser() 
	{
		User user=new User();
		
		user.setUserId(1);
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
		
		policy.setPolicyId(1);
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
		
		beneficiary.setBeneficiaryId(2);
		beneficiary.setUser(getDummyUser());
		beneficiary.setPolicies(getDummyPolicy());
		beneficiary.setName("Vamsi");
		beneficiary.setRelationship("Brother");
		
		return beneficiary;
	}
	
	public BeneficiaryDTO getDummyBeneficiaryDTO()
	{
		BeneficiaryDTO beneficiaryDTO = new BeneficiaryDTO();
		
		beneficiaryDTO.setBeneficiaryId(getDummyBeneficiary().getBeneficiaryId());
		beneficiaryDTO.setUserId(getDummyBeneficiary().getUser().getUserId());
		beneficiaryDTO.setPolicyId(getDummyBeneficiary().getPolicies().getPolicyId());
		beneficiaryDTO.setName(getDummyBeneficiary().getName());
		beneficiaryDTO.setRelationship(getDummyBeneficiary().getRelationship());
		
		return beneficiaryDTO;
	}
	
	@Test
	void addBeneficiary_should_return_added_beneficiary() 
	{
		//Given
		Beneficiary beneficiary=getDummyBeneficiary();
		
		
		BeneficiaryDTO beneficiaryDTO =getDummyBeneficiaryDTO();
        
		//Mock mapper behavior
		when(beneficiaryMapper.convertFromDTO(beneficiaryDTO)).thenReturn(beneficiary);
		when(beneficiaryMapper.convertToDTO(beneficiary)).thenReturn(beneficiaryDTO);
		
		//Mock repository behavior
		when(beneficiaryRepository.save(beneficiary)).thenReturn(beneficiary);
		
		//When
		BeneficiaryDTO addedBeneficiaryDTO=beneficiaryService.saveBeneficiary(beneficiaryDTO);
	
		//Then
		//Ensure addedBeneficiaryDTO is not null
		Assertions.assertNotNull(addedBeneficiaryDTO);
		Assertions.assertEquals(beneficiary.getBeneficiaryId(),addedBeneficiaryDTO.getBeneficiaryId());
		verify(beneficiaryMapper).convertFromDTO(beneficiaryDTO);
		verify(beneficiaryRepository).save(beneficiary);
		
	}
	
	@Test
	void deleteBeneficiary_should_return_true() {
		//Given
        Beneficiary beneficiary = Mockito.mock(Beneficiary.class);
        User user=Mockito.mock(User.class);
		Policy policy=Mockito.mock(Policy.class);
		when(beneficiary.getUser()).thenReturn(user);
		when(beneficiary.getPolicies()).thenReturn(policy);
		
		doNothing().when(user).removeBeneficiary(beneficiary);
		doNothing().when(policy).removeBeneficiary(beneficiary);
		when(beneficiaryRepository.findById(2)).thenReturn(Optional.of(beneficiary));
		
		//When
		boolean isDeleted=beneficiaryService.deleteBeneficiary(2);
		
		// Then
        Assertions.assertTrue(isDeleted);
        verify(beneficiaryRepository).findById(2);
        verify(beneficiaryRepository).delete(beneficiary);
	}
	
	@Test
	void updateBeneficiary_should_return_updated_Beneficiary() {
	    // Given
	    Beneficiary beneficiary = getDummyBeneficiary();
	    BeneficiaryDTO updatedBeneficiaryDTO = getDummyBeneficiaryDTO();

	    // Mock mapper behavior
	    when(beneficiaryMapper.convertToDTO(beneficiary)).thenReturn(updatedBeneficiaryDTO);

	    // Mock repository behavior
	    when(beneficiaryRepository.save(beneficiary)).thenReturn(beneficiary);
	    when(beneficiaryRepository.findById(2)).thenReturn(Optional.of(beneficiary));

	    // When
	    BeneficiaryDTO returnedUpdatedBeneficiaryDTO = beneficiaryService.updateBeneficiary(2, updatedBeneficiaryDTO);

	    // Then
	    Assertions.assertEquals(updatedBeneficiaryDTO.getBeneficiaryId(), returnedUpdatedBeneficiaryDTO.getBeneficiaryId());
	    verify(beneficiaryRepository).findById(2);
	    verify(beneficiaryRepository).save(beneficiary);
	}

	@Test
	void getByUserId_should_return_beneficiaries_list() {
	    // Given
	    int userId = 1; 
	    User user = getDummyUser(); 
	    List<Beneficiary> dummyBeneficiaries = Arrays.asList(getDummyBeneficiary(), getDummyBeneficiary()); 
	    user.setBeneficiaries(dummyBeneficiaries); // Set the dummy beneficiaries list to the user

	    // Mock repository behavior
	    when(userRepository.findById(userId)).thenReturn(Optional.of(user));
	    
	    // Mock mapper behavior
	    for (Beneficiary beneficiary : dummyBeneficiaries) {
	        when(beneficiaryMapper.convertToDTO(beneficiary)).thenReturn(new BeneficiaryDTO()); 
	    }

	    // When
	    List<BeneficiaryDTO> returnedBeneficiariesDTO = beneficiaryService.getByUserId(userId);

	    // Then
	    Assertions.assertEquals(dummyBeneficiaries.size(), returnedBeneficiariesDTO.size());
	    for (int i = 0; i < dummyBeneficiaries.size(); i++) 
	    {
	        BeneficiaryDTO expectedDTO = beneficiaryMapper.convertToDTO(dummyBeneficiaries.get(i));
	        BeneficiaryDTO actualDTO = returnedBeneficiariesDTO.get(i);
	        Assertions.assertEquals(expectedDTO.getBeneficiaryId(), actualDTO.getBeneficiaryId());
	    }
	    verify(userRepository).findById(userId);
	}
	
	@Test
	void getByPolicyId_should_return_beneficiaries_list()
	{
	    // Given
	    int policyId = 1;
	    Policy policy = getDummyPolicy(); 
	    List<Beneficiary> dummyBeneficiaries = Arrays.asList(getDummyBeneficiary(), getDummyBeneficiary());
	    policy.setBeneficiaries(dummyBeneficiaries); // Set the dummy beneficiaries list to the policy

	    // Mock repository behavior
	    when(policyRepository.findById(policyId)).thenReturn(Optional.of(policy));
	    
	    // Mock mapper behavior
	    for (Beneficiary beneficiary : dummyBeneficiaries) {
	        when(beneficiaryMapper.convertToDTO(beneficiary)).thenReturn(new BeneficiaryDTO()); 
	    }

	    // When
	    List<BeneficiaryDTO> returnedBeneficiariesDTO = beneficiaryService.getByPolicyId(policyId);

	    // Then
	    Assertions.assertEquals(dummyBeneficiaries.size(), returnedBeneficiariesDTO.size());
	    for (int i = 0; i < dummyBeneficiaries.size(); i++) 
	    {
	        BeneficiaryDTO expectedDTO = beneficiaryMapper.convertToDTO(dummyBeneficiaries.get(i));
	        BeneficiaryDTO actualDTO = returnedBeneficiariesDTO.get(i);
	        Assertions.assertEquals(expectedDTO.getBeneficiaryId(), actualDTO.getBeneficiaryId());
	    }
	    verify(policyRepository).findById(policyId);
	}



	
	
	
}
package com.testing.mockito;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class AppTest {
	
	public List mockedList;
	List mammals;
	@Before
	public void setUp() {
		mockedList = mock(LinkedList.class);
	}
	
	@Test
	public void testMockitoInit() {
		doThrow(new RuntimeException()).when(mockedList).clear();

		//following throws RuntimeException:
		//mockedList.clear();	
	}
	
	@Test
	public void testMockto() {
		 mammals= new ArrayList();
		//using mock
		mockedList.add("once");

		mockedList.add("twice");
		mockedList.add("twice");

		mockedList.add("three times");
		mockedList.add("three times");
		mockedList.add("three times");

		//following two verifications work exactly the same - times(1) is used by default
		verify(mockedList).add("once");
		verify(mockedList, times(1)).add("once");

		//exact number of invocations verification
		verify(mockedList, times(2)).add("twice");
		verify(mockedList, times(3)).add("three times");

		//verification using never(). never() is an alias to times(0)
		verify(mockedList, never()).add("never happened");

		//verification using atLeast()/atMost()
		verify(mockedList, atLeastOnce()).add("three times");
		//verify(mockedList, atLeast(2)).add("five times");
		verify(mockedList, atMost(5)).add("three times");

	}
	
	@Test
	public void testMock2() {
		// A. Single mock whose methods must be invoked in a particular order
		List singleMock = mock(List.class);

		//using a single mock
		singleMock.add("was added first");
		singleMock.add("was added second");

		//create an inOrder verifier for a single mock
		InOrder inOrder = inOrder(singleMock);

		//following will make sure that add is first called with "was added first, then with "was added second"
		inOrder.verify(singleMock).add("was added first");
		inOrder.verify(singleMock).add("was added second");

		// B. Multiple mocks that must be used in a particular order
		List firstMock = mock(List.class);
		List secondMock = mock(List.class);

		//using mocks
		firstMock.add("was called first");
		secondMock.add("was called second");

		//create inOrder object passing any mocks that need to be verified in order
		InOrder inOrder1 = inOrder(firstMock, secondMock);

		//following will make sure that firstMock was called before secondMock
		inOrder1.verify(firstMock).add("was called first");
		inOrder1.verify(secondMock).add("was called second");

		// Oh, and A + B can be mixed together at will
		
	}
	

}

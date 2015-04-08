package oracle.sgt.mapviewer.junit.testsuites;

import oracle.sgt.mapviewer.junit.Admin;
import oracle.sgt.mapviewer.junit.MapBuilder;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	
	Admin.class,
	MapBuilder.class,
})
public class SanityTestSuite {

}

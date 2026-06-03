package sh.edelbyte.demo;

import org.junit.jupiter.api.Test;
import sh.edelbyte.demo.Excuse.ExcuseCategory;

import static org.assertj.core.api.Assertions.assertThat;

class ExcuseGeneratorTest {
    
    @Test
    void testGetRandomSlowBuildExcuse() {
        ExcuseGenerator generator = new ExcuseGenerator(42L);
        Excuse excuse = generator.getRandomSlowBuildExcuse();
        
        assertThat(excuse).isNotNull();
        assertThat(excuse.getCategory()).isEqualTo(ExcuseCategory.SLOW_BUILD);
        assertThat(excuse.getDescription()).isNotEmpty();
    }
    
    @Test
    void testGetRandomFailedBuildExcuse() {
        ExcuseGenerator generator = new ExcuseGenerator(42L);
        Excuse excuse = generator.getRandomFailedBuildExcuse();
        
        assertThat(excuse).isNotNull();
        assertThat(excuse.getCategory()).isEqualTo(ExcuseCategory.FAILED_BUILD);
        assertThat(excuse.getDescription()).isNotEmpty();
    }
    
    @Test
    void testGetRandomExcuse() {
        ExcuseGenerator generator = new ExcuseGenerator(42L);
        Excuse excuse = generator.getRandomExcuse();
        
        assertThat(excuse).isNotNull();
        assertThat(excuse.getCategory()).isIn(ExcuseCategory.SLOW_BUILD, ExcuseCategory.FAILED_BUILD);
        assertThat(excuse.getDescription()).isNotEmpty();
    }
    
    @Test
    void testGetRandomExcuseForCategory() {
        ExcuseGenerator generator = new ExcuseGenerator(42L);
        
        Excuse slowBuild = generator.getRandomExcuseForCategory(ExcuseCategory.SLOW_BUILD);
        assertThat(slowBuild.getCategory()).isEqualTo(ExcuseCategory.SLOW_BUILD);
        
        Excuse failedBuild = generator.getRandomExcuseForCategory(ExcuseCategory.FAILED_BUILD);
        assertThat(failedBuild.getCategory()).isEqualTo(ExcuseCategory.FAILED_BUILD);
    }
    
    @Test
    void testExcuseValueObject() {
        Excuse excuse = new Excuse(ExcuseCategory.SLOW_BUILD, "Test excuse");
        
        assertThat(excuse.getCategory()).isEqualTo(ExcuseCategory.SLOW_BUILD);
        assertThat(excuse.getDescription()).isEqualTo("Test excuse");
    }
}

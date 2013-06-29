package tddfirststeps;


import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class GetReadyTest {

    public static final String AYE_AYE = "aye aye";

    @Test
  public void pair_should_be_ready_for_the_kata() {
        assertThat(i_am_ready_for_the_kata(), is(AYE_AYE));
    }

    private String i_am_ready_for_the_kata() {
       return AYE_AYE;
    }

}

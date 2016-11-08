package lambdas;

import javax.annotation.CheckForNull;
import edu.umd.cs.findbugs.annotations.ExpectWarning;

public class Issue20 {

  /**
   * @return may be null
   */
  @CheckForNull
  public static String methodThatMightReturnNull() {
    if (Boolean.getBoolean("test")) {
      return null;
    }

    return "test";
  }

  /**
   */
  // Can't currently annotate lambdas, and the error is reported there, not on this method
  //@ExpectWarning("NP_NULL_ON_SOME_PATH_FROM_RETURN_VALUE")
  public void testNPLambda() {
    Runnable lambda = () -> {
      String maybeNull = methodThatMightReturnNull();

      // would expect findbugs warning about possible null dereferencing here
      boolean empty = maybeNull.isEmpty();
      System.out.println(empty);
    };
    lambda.run();
  }

}

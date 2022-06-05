public class ClassNameHere {
   public static void main(String[] args) {
      int count = 0;
      String str = "";
      while (count < 5) {
         str += "*";
         System.out.println(str);
         count += 1;
      }
   }
}


public class ClassNameHere2 {
   public static void drawTriangle(int x) {
      int count = 0;
      String str = "";
      while (count < x) {
         str += "*";
         System.out.println(str);
         count += 1;
      }

   }
   public static void main(String[] args) {
      drawTriangle(10);
   }
}

/** Exercise 2 —— Array */
public class ClassNameHere3 {
    /** Returns the maximum value from m. */
    public static int max(int[] m) {
       int ans = 0;
       for (int i = 0; i < m.length; i += 1) {
          if (m[i] > ans) {
             ans = m[i];
          }
       }
       return ans;
    }

    public static void main(String[] args) {
       int[] numbers = new int[]{9, 2, 15, 2, 22, 10, 6};
       int res = max(numbers);
       System.out.println(res);
    }
}


/** Exercise3 */

public class BreakContinue {
  public static void windowPosSum(int[] a, int n) {
    /** your code here */
     for (int i = 0; i < a.length; i += 1) {
        if (a[i] < 0 || i == a.length - 1) {
           continue;
        }
        int end = Math.min((i + n), a.length - 1);
        for (int j = i + 1; j <= end; j += 1) {
           a[i] += a[j];
        }
     }
  }

  public static void main(String[] args) {
    int[] a = {1, 2, -3, 4, 5, 4};
    int n = 3;
    windowPosSum(a, n);

    // Should print 4, 8, -3, 13, 9, 4
    System.out.println(java.util.Arrays.toString(a));
  }
}


/**  Squaring a List */
public class squaringList {

  public static IntList square(IntList L) {
    if (L == null) {
      return L;
    }
    IntList node = new IntNode(L.first * L.first, square(L.rest));
    return node;
  }

  public static IntList squareIterative(IntList L) {
    if (L == null) {
      return L;
    }
    IntList B = L.rest;
    IntList SquaredList = new IntList(L.first * L.first, null); // 用于保存新建的List的头结点，便于直接返回
    IntList C = SquaredList;
    while (B != null) {
      C.rest = new IntList(B.first * B.first, null);
      B = B.rest;
      C = C.rest;
    }
    return SquaredList;

    }

  }

  public static IntList squareMutativeRecursive(IntList L) {
    if (L == null) {
      return L;
    } else {
      L.first = L.first * L.first;
      squareMutative(L.rest);
    }
    return L;
  }

  public static IntList squareMutativeIterative(IntList L) {
    IntList head = L;
    while (head != null) {
      head.first *= head.first;
      head = head.rest;
    }
    return L;
  }

}

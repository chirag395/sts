//package com.test;
//
//interface Inf3{
//	void add();
//	interface Inf4{
//		void sub();
//	}
//}
//
//class Impl4 implements Inf3.Inf4{
//	@Override
//	public void sub() {
//		System.out.println("sub()");
//	}
//}
//
//class Impl4 implements Inf3{
//	public void add() {
//		System.out.println("add()");
//	}
//}
//
//public class Exp12 {
//
//	public static void main(String[] args) {
//	
//
//	}
//
//}






package com.examples;

interface Inf3 {
    void add();

    // Nested interface inside Inf3
    interface Inf4 {
        void sub();
    }
}

// Implements the outer interface Inf3
class ImplAdd implements Inf3 {
    @Override
    public void add() {
        System.out.println("add()");
    }
}

// Implements the nested interface Inf3.Inf4
class ImplSub implements Inf3.Inf4 {
    @Override
    public void sub() {
        System.out.println("sub()");
    }
}

public class Exp12 {
    public static void main(String[] args) {
        // Using implementation of Inf3
        Inf3 addImpl = new ImplAdd();
        addImpl.add();

        // Using implementation of Inf3.Inf4 (nested interface)
        Inf3.Inf4 subImpl = new ImplSub();
        subImpl.sub();

        // Anonymous class for Inf3
        Inf3 addAnon = new Inf3() {
            @Override
            public void add() {
                System.out.println("add() from anonymous class");
            }
        };
        addAnon.add();

        // Anonymous class for Inf3.Inf4
        Inf3.Inf4 subAnon = new Inf3.Inf4() {
            @Override
            public void sub() {
                System.out.println("sub() from anonymous class");
            }
        };
        subAnon.sub();

      
    }
}

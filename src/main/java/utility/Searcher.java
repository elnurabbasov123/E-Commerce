//package utility;
//import model.enums.Exceptions;
//import model.exception.ObjectNotFound;
//
//import java.util.List;
//import java.util.function.Predicate;
//public class Searcher {
//    public static <T> T search(List<T> list, Predicate<T> field) {
//        T t = null;
//        boolean ok = false;
//        for (T element : list) {
//            if (field.test(element)) {
//                t = element;
//                ok = true;
//            }
//        }
//        if (ok) {
//            return t;
//        } else {
//            throw new ObjectNotFound(1,"Object",Exceptions.OBJECT_NOT_FOUND);
//        }
//    }
//}
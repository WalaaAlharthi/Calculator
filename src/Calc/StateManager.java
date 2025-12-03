
package Calc;

public class StateManager {

    private String current = "";   // الرقم الذي يتم إدخاله الآن
    private String previous = "";  // الرقم الأول في العملية
    private String operator = "";  // العملية (+، -، ×، ÷ ...)
    private float memoryValue = 0;
  
    // -----------------------------------------------------
    //                     GETTERS
    // -----------------------------------------------------

    public String getCurrent() { return current; }
    public String getPrevious() { return previous; }
    public String getOperator() { return operator; }
    public float getMemory() { return memoryValue; }
    public void setMemory(float v) { memoryValue = v; }
    public void clearMemory() { memoryValue = 0; }
 
    // -----------------------------------------------------
    //                      LOGIC
    // -----------------------------------------------------

    public boolean hasPendingOperation() {
        return !previous.isEmpty() && !operator.isEmpty();
    }

    public boolean canCompute() {
        return !previous.isEmpty() && !current.isEmpty() && !operator.isEmpty();
    }

    public void setCurrent(String value) {
        this.current = (value == null ? "" : value);
    }

    public void setPrevious(String value) {
        this.previous = (value == null ? "" : value);
    }

    public void setOperator(String value) {
        this.operator = (value == null ? "" : value);
    }

    // -----------------------------------------------------
    //                 CLEAR STATE
    // -----------------------------------------------------

    public void clearAll() {
        current = "";
        previous = "";
        operator = "";
    }

    // -----------------------------------------------------
    //            WHEN USER SELECTS AN OPERATION
    // -----------------------------------------------------

    /**
     *
     * current → previous
     * current → ""
     * operator → op
     */
    public void startOperation(String op) {

    // إذا كنا رجعنا بحالة Undo → previous موجود و current موجود
    // لا نسمح بتغيير العملية تلقائياً
    if (!previous.isEmpty() && !current.isEmpty()) {
        operator = op;   // تغيير العملية فقط، لا نمسح شيء
        return;
    }

    // الحالة الطبيعية (أول مرة يختار العملية)
    if (!current.isEmpty()) {
        previous = current;
        current = "";
        operator = op;
        return;
    }

    // آخر خيار: إذا previous موجود و current فارغ → فقط غيّر العملية
    if (!previous.isEmpty()) {
        operator = op;
    }
}

    // -----------------------------------------------------
    //                AFTER EQUALS (=)
    // -----------------------------------------------------

    
    public void applyResult(String result) {
    current = result;
    previous = "";   // نخفي كل شيء فوق
    operator = "";
}
    private final java.util.List<String> history = new java.util.ArrayList<>();

public void addHistory(String entry) {
    history.add(entry);
}

public java.util.List<String> getHistory() {
    return history;
}




}

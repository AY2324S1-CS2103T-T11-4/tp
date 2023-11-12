package seedu.flashlingo.session;

/**
 * The SessionManager class implements the Session interface and is responsible for managing language learning sessions.
 */
public class SessionManager {
    private static SessionManager instance = null;
    private static boolean isReviewSession = false;

    private SessionManager() {
        // Private constructor to prevent external instantiation.
    }

    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }
    public void setSession(boolean editedReivewSession) {
        isReviewSession = editedReivewSession;
    }
    public boolean isReviewSession() {
        return isReviewSession;
    }
}


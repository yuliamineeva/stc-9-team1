package ru.innopolis.stc9.t1.db.dao;

//@Repository
public class LessonDAOImpl {
        /*

public class LessonDAOImpl implements LessonDAO {
    private final static Logger logger = Logger.getLogger(LessonDAOImpl.class);
    private static ConnectionManager connectionManager = ConnectionManagerJDBCImpl.getInstance();


    @Override
    public boolean addLesson(Lesson lesson) {
        if (lesson == null) return false;
        Connection connection = connectionManager.getConnection();
        int countRow = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO lessons " +
                    "(tutor_id, group_id, topic, date) VALUES (?, ?, ?, ?)");
            setStatementForAdd(lesson, statement);
            countRow = statement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            logger.error("Error trying to add lesson to DB", e);
        }
        return countRow > 0;
    }

    private void setStatementForAdd(Lesson lesson, PreparedStatement statement) throws SQLException {
        statement.setInt(1, lesson.getTutor_id());
        statement.setInt(2, lesson.getGroup_id());
        statement.setString(3, lesson.getTopic());
        statement.setDate(4, new java.sql.Date(lesson.getDate().getTime()));
    }

    @Override
    public Lesson getLessonById(int id) {
        Connection connection = connectionManager.getConnection();
        Lesson lesson = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * " +
                    "FROM lessons INNER JOIN users ON lessons.tutor_id = users.user_id " +
                    "INNER JOIN groups ON lessons.group_id = groups.group_id " +
                    "WHERE lessons.lsn_id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            lesson = getLessonFromResultset(resultSet);
            connection.close();
        } catch (SQLException e) {
            logger.error("Error trying to get lesson by ID from DB", e);
        }
        return lesson;
    }

    private Lesson getLessonFromResultset(ResultSet resultSet) throws SQLException {
        if (resultSet == null) return null;
        Lesson lesson = null;
        if (resultSet.next()) {
            lesson = new Lesson(
                    resultSet.getInt("lsn_id"),
                    resultSet.getInt("tutor_id"),
                    resultSet.getInt("group_id"),
                    resultSet.getString("topic"),
                    resultSet.getDate("date"),
                    resultSet.getString("fio"),
                    resultSet.getString("name"));
        }
        return lesson;
    }

    @Override
    public List<Lesson> getAllLessons() {
        Connection connection = connectionManager.getConnection();
        List<Lesson> lessons = null;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * " +
                    "FROM lessons INNER JOIN users ON lessons.tutor_id = users.user_id " +
                    "INNER JOIN groups ON lessons.group_id = groups.group_id ");
            lessons = getLessonsListFromResultset(resultSet);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lessons;
    }

    private ArrayList<Lesson> getLessonsListFromResultset(ResultSet resultSet) throws SQLException {
        ArrayList<Lesson> lessonsArrayList = new ArrayList<>();
        Lesson lesson;
        while (resultSet.next()) {
            lesson = new Lesson(
                    resultSet.getInt("lsn_id"),
                    resultSet.getInt("tutor_id"),
                    resultSet.getInt("group_id"),
                    resultSet.getString("topic"),
                    resultSet.getDate("date"),
                    resultSet.getString("fio"),
                    resultSet.getString("name"));
            lessonsArrayList.add(lesson);
        }
        return lessonsArrayList.size() == 0 ? null : lessonsArrayList;
    }

    @Override
    public List<Lesson> getLessonsByGroup(int group_id) {
        Connection connection = connectionManager.getConnection();
        List<Lesson> lessonsByGroup = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * " +
                    "FROM lessons INNER JOIN users ON lessons.tutor_id = users.user_id " +
                    "INNER JOIN groups ON lessons.group_id = groups.group_id " +
                    "WHERE lessons.group_id = ?");
            statement.setInt(1, group_id);
            ResultSet resultSet = statement.executeQuery();
            lessonsByGroup = getLessonsListFromResultset(resultSet);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lessonsByGroup;
    }

    @Override
    public List<Lesson> getLessonsByTutor(int tutor_id) {
        Connection connection = connectionManager.getConnection();
        List<Lesson> lessonsByTutor = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * " +
                    "FROM lessons INNER JOIN users ON lessons.tutor_id = users.user_id " +
                    "INNER JOIN groups ON lessons.group_id = groups.group_id " +
                    "WHERE lessons.tutor_id = ?");
            statement.setInt(1, tutor_id);
            ResultSet resultSet = statement.executeQuery();
            lessonsByTutor = getLessonsListFromResultset(resultSet);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lessonsByTutor;
    }

    @Override
    public List<Lesson> getLessonsByDate(Date date) {
        Connection connection = connectionManager.getConnection();
        List<Lesson> lessonsByDate = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * " +
                    "FROM lessons INNER JOIN users ON lessons.tutor_id = users.user_id " +
                    "INNER JOIN groups ON lessons.group_id = groups.group_id " +
                    "WHERE DATE(lessons.date) = ? + INTERVAL 1 DAY");
            statement.setDate(1, new java.sql.Date(date.getTime()));
            ResultSet resultSet = statement.executeQuery();
            lessonsByDate = getLessonsListFromResultset(resultSet);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lessonsByDate;
    }

    @Override
    public boolean updateLesson(Lesson lesson) {
        if (lesson == null) return false;
        Connection connection = connectionManager.getConnection();
        int countRow = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE lessons " +
                    "SET tutor_id = ?, group_id = ?, topic = ?, date = ? WHERE lsn_id = ?");
            setStatementForUpdate(lesson, statement);
            countRow = statement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            logger.error("Error trying to update lesson", e);
        }
        return countRow > 0;
    }

    private void setStatementForUpdate(Lesson lesson, PreparedStatement statement) throws SQLException {
        setStatementForAdd(lesson, statement);
        statement.setInt(5, lesson.getLsn_id());
    }

    @Override
    public boolean deleteLessonById(int id) {
        Connection connection = connectionManager.getConnection();
        int countRow = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM lessons " +
                    "WHERE lsn_id = ?");
            statement.setInt(1, id);
            countRow = statement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            logger.error("Error trying to delete lesson", e);
        }
        return countRow > 0;
    }
    */
}

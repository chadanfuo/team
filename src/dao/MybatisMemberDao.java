package dao;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import model.Member;
import mybatis.AbstractRepository;

public class MybatisMemberDao extends AbstractRepository
{
    private final String namespace = "mybatis.Member";

    private static MybatisMemberDao instance = new MybatisMemberDao();
    
    public static MybatisMemberDao getInstance()
    {
        return instance;
    }
    private MybatisMemberDao(){}

    public Member selectById(String email) 
    {
        SqlSession sqlSession = getSqlSessionFactory().openSession();
        try
        {
            String statement = namespace + ".selectById";
            return sqlSession.selectOne(statement, email);
        }
        finally
        {
            sqlSession.close();
        }
    }
    
    public void insert(Member mem) 
    {
        SqlSession sqlSession = getSqlSessionFactory().openSession();
        try
        {
            String statement = namespace + ".insert";
            sqlSession.insert(statement, mem);
            sqlSession.commit();
        }
        finally
        {
            sqlSession.close();
        }
    }
    
    public void update(Member mem) 
    {
       
        SqlSession sqlSession = getSqlSessionFactory().openSession();
        try
        {
            String statement = namespace + ".update";
            sqlSession.update(statement, mem);
            sqlSession.commit();
        }
        finally
        {
            sqlSession.close();
        }
    }
    
    public List<Member> memberList() 
    {
        SqlSession sqlSession = getSqlSessionFactory().openSession();
        try
        {
            String statement = namespace + ".memberList";
            return sqlSession.selectList(statement);
        }
        finally
        {
            sqlSession.close();
        }
    }
}

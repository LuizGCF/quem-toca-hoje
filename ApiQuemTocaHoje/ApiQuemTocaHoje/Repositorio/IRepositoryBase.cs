using System;
using System.Collections.Generic;
using System.Linq;
using System.Linq.Expressions;
using System.Threading.Tasks;

namespace ApiQuemTocaHoje.Repositorio
{
    public interface IRepositoryBase<T> where T : class
    {
        void Add(T entity);

        void AddAll(List<T> entity);

        void Edit(T entity);

        void Delete(T entity);

        void DeleteAll(Expression<Func<T, bool>> filter = null);

        List<T> Get(Expression<Func<T, bool>> filter = null, Func<IQueryable<T>, IOrderedQueryable<T>> orderBy = null);
    }
}

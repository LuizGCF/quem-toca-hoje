using ApiQuemTocaHoje.Banco;
using ApiQuemTocaHoje.Migrations;
using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Threading.Tasks;

namespace ApiQuemTocaHoje.Repositorio
{
    public class RepositoryBase<T> : IDisposable, IRepositoryBase<T> where T : class
    {
        internal ContextoBanco Contexto;
        internal DbSet<T> DbSet;

        public RepositoryBase(ContextoBanco contexto)
        {
            Contexto = contexto;
            DbSet = contexto.Set<T>();
        }

        public void Add(T entity)
        {
            DbSet.Add(entity);
            Contexto.SaveChanges();
        }

        public void AddAll(List<T> entity)
        {
            foreach (var item in entity)
            {
                DbSet.Add(item);
            }
            Contexto.SaveChanges();
        }

        public void Delete(T entity)
        {
            if (Contexto.Entry(entity).State == EntityState.Detached)
            {
                DbSet.Attach(entity);
            }
            DbSet.Remove(entity);
            Contexto.SaveChanges();
        }

        public void DeleteAll(System.Linq.Expressions.Expression<Func<T, bool>> filter = null)
        {
            IQueryable<T> query = DbSet;
            List<T> listDelete = query.Where(filter).ToList();

            foreach (var item in listDelete)
            {
                DbSet.Remove(item);
            }
            Contexto.SaveChanges();
        }

        public void Dispose()
        {
            DbSet = null;
            Contexto.Dispose();
            GC.SuppressFinalize(this);
        }

        public void Edit(T entity)
        {
            var entrada = Contexto.Entry<T>(entity);
            var chave = DbSet.Create().GetType().GetProperty("id").GetValue(entity);

            if (entrada.State == EntityState.Detached)
            {
                var set = Contexto.Set<T>();
                T attachedEntity = set.Find(chave);  // access the key 
                if (attachedEntity != null)
                {
                    var attachedEntry = Contexto.Entry(attachedEntity);
                    attachedEntry.CurrentValues.SetValues(entity);
                }
                else
                {
                    entrada.State = EntityState.Modified; // attach the entity 
                }
            }

            Contexto.SaveChanges();
        }

        public List<T> Get(System.Linq.Expressions.Expression<Func<T, bool>> filter = null, Func<IQueryable<T>, IOrderedQueryable<T>> orderBy = null)
        {
            IQueryable<T> query = DbSet;

            if (filter != null)
            {
                query = query.Where(filter);
            }

            if (orderBy != null)
            {
                return orderBy(query).ToList();
            }
            else
            {
                return query.ToList();
            }
        }
    }
}

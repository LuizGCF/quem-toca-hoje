using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Threading.Tasks;
using ApiQuemTocaHoje.Banco;
using ApiQuemTocaHoje.Models;
using ApiQuemTocaHoje.Repositorio;
using ApiQuemTocaHoje.ViewModels;
using Microsoft.AspNetCore.Mvc;
using Newtonsoft.Json;

namespace ApiQuemTocaHoje.Controllers
{
    [Route("api/autenticacao")]
    [ApiController]
    public class AutenticacaoController : Controller
    {
        private readonly ContextoBanco Contexto;

        private RepositoryBase<Autenticacao> repositorioautenticacao;

        public RepositoryBase<Autenticacao> RepositorioAutenticacao
        {
            get
            {
                if (repositorioautenticacao == null)
                {
                    repositorioautenticacao = new RepositoryBase<Autenticacao>(Contexto);
                }

                return repositorioautenticacao;
            }
            set { repositorioautenticacao = value; }
        }


        public AutenticacaoController(ContextoBanco contexto)
        {
            Contexto = contexto;
        }

        // GET api/autenticacao
        [HttpGet]
        public ActionResult<IEnumerable<Autenticacao>> Get()
        {
            var item = RepositorioAutenticacao.DbSet.ToList();
            if (item == null)
                return NotFound();

            return item;
        }

        // GET api/autenticacao/5
        [HttpGet("{id}")]
        public async Task<ActionResult<Autenticacao>> GetAsync(int id)
        {
            var item = await RepositorioAutenticacao.DbSet.FindAsync(id);
            if (item == null)
                return NotFound();

            return item;
            //return "value";
        }

        // GET api/autenticacao/5
        [HttpGet("login")]
        public async Task<ActionResult<Autenticacao>> GetPorUsuarioAsync([FromQuery] string login, [FromQuery] string senha)
        {
            var item = await RepositorioAutenticacao.DbSet.Where(x => x.LoginAutenticacao.Equals(login) && x.SenhaAutenticacao.Equals(senha)).SingleOrDefaultAsync();
            if (item == null)
                return NotFound();
            else
            {
                Contexto.Entry(item).State = EntityState.Modified;
                item.DataUltimoLogin = DateTime.Now;
                await Contexto.SaveChangesAsync();
            }
            return item;
            //return "value";
        }

        // POST api/autenticacao
        //no post preciso colocar postman que o content-type é do tipo application/json
        [HttpPost]
        public async Task<ActionResult<Autenticacao>> PostAsync([FromBody] AutenticacaoViewModel values)
        {
            Autenticacao item = new Autenticacao()
            {
                DataCriacao = DateTime.Now,
                DataUltimoLogin = DateTime.Now,
                EmailAutenticacao = values.Email,
                LoginAutenticacao = values.Login,
                SenhaAutenticacao = values.Senha,
                Registro = values.Registro,
                TipoUsuarioAutenticacao = values.TipoUsuario
            };
            RepositorioAutenticacao.DbSet.Add(item);
            await RepositorioAutenticacao.Contexto.SaveChangesAsync();

            return Ok(item);//CreatedAtAction(nameof(Autenticacao), new { id = item.IdAutenticacao}, item);
        }

        // PUT api/autenticacao/5
        [HttpPut("{id}")]
        public async Task<IActionResult> Put(int id, [FromBody] Autenticacao item)
        {
            if (id != item.IdAutenticacao)
            {
                return BadRequest();
            }

            Contexto.Entry(item).State = EntityState.Modified;
            await Contexto.SaveChangesAsync();

            return NoContent();
        }

        // DELETE api/autenticacao/5
        [HttpDelete("{id}")]
        public async Task<ActionResult> DeleteAsync(int id)
        {
            var todoItem = await RepositorioAutenticacao.DbSet.FindAsync(id);

            if (todoItem == null)
            {
                return NotFound();
            }

            RepositorioAutenticacao.DbSet.Remove(todoItem);
            await RepositorioAutenticacao.Contexto.SaveChangesAsync();

            return NoContent();
        }

    }
}

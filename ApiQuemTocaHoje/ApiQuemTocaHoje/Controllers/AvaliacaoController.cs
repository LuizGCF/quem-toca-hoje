using ApiQuemTocaHoje.Banco;
using ApiQuemTocaHoje.Models;
using ApiQuemTocaHoje.Repositorio;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Threading.Tasks;

namespace ApiQuemTocaHoje.Controllers
{

    [Route("api/avaliacao")]
    [ApiController]
    public class AvaliacaoController : Controller
    {
        private readonly ContextoBanco Contexto;

        private RepositoryBase<Avaliacao> repositorioavaliacao;

        public RepositoryBase<Avaliacao> RepositorioAvaliacao
        {
            get
            {
                if (repositorioavaliacao == null)
                {
                    repositorioavaliacao = new RepositoryBase<Avaliacao>(Contexto);
                }

                return repositorioavaliacao;
            }
            set { repositorioavaliacao = value; }
        }


        public AvaliacaoController(ContextoBanco contexto)
        {
            Contexto = contexto;
        }

        // GET api/autenticacao
        [HttpGet]
        public ActionResult<IEnumerable<Avaliacao>> Get()
        {
            var item = RepositorioAvaliacao.DbSet.ToList();
            if (item == null)
                return NotFound();

            return item;
        }

        // GET api/autenticacao/5
        [HttpGet("{id}")]
        public async Task<ActionResult<Avaliacao>> GetAsync(int id)
        {
            var item = await RepositorioAvaliacao.DbSet.FindAsync(id);
            if (item == null)
                return NotFound();

            return item;
            //return "value";
        }

        // POST api/autenticacao
        //no post preciso colocar postman que o content-type é do tipo application/json
        [HttpPost]
        public async Task<ActionResult<Avaliacao>> PostAsync([FromBody] Avaliacao item)
        {
            RepositorioAvaliacao.DbSet.Add(item);
            await RepositorioAvaliacao.Contexto.SaveChangesAsync();

            return CreatedAtAction(nameof(Avaliacao), new { id = item.IdAvaliacao }, item);
        }

        // PUT api/autenticacao/5
        [HttpPut("{id}")]
        public async Task<IActionResult> Put(int id, [FromBody] Avaliacao item)
        {
            if (id != item.IdAvaliacao)
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
            var todoItem = await RepositorioAvaliacao.DbSet.FindAsync(id);

            if (todoItem == null)
            {
                return NotFound();
            }

            RepositorioAvaliacao.DbSet.Remove(todoItem);
            await RepositorioAvaliacao.Contexto.SaveChangesAsync();

            return NoContent();
        }
    }
}

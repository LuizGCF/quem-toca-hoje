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
    [Route("api/banda")]
    [ApiController]
    public class BandaController : Controller
    {
        private readonly ContextoBanco Contexto;

        private RepositoryBase<Banda> repositorioespectador;

        public RepositoryBase<Banda> RespositorioEspectador
        {
            get
            {
                if (repositorioespectador == null)
                {
                    repositorioespectador = new RepositoryBase<Banda>(Contexto);
                }

                return repositorioespectador;
            }
            set { repositorioespectador = value; }
        }


        public BandaController(ContextoBanco contexto)
        {
            Contexto = contexto;
        }

        // GET api/autenticacao
        [HttpGet]
        public ActionResult<IEnumerable<Banda>> Get()
        {
            var item = RespositorioEspectador.DbSet.ToList();
            if (item == null)
                return NotFound();

            return item;
        }

        // GET api/autenticacao/5
        [HttpGet("{id}")]
        public async Task<ActionResult<Banda>> GetAsync(int id)
        {
            var item = await RespositorioEspectador.DbSet.FindAsync(id);
            if (item == null)
                return NotFound();

            return item;
            //return "value";
        }

        // POST api/autenticacao
        //no post preciso colocar postman que o content-type é do tipo application/json
        [HttpPost]
        public async Task<ActionResult<Banda>> PostAsync([FromBody] Banda item)
        {
            RespositorioEspectador.DbSet.Add(item);
            await RespositorioEspectador.Contexto.SaveChangesAsync();

            return CreatedAtAction(nameof(Banda), new { id = item.IdBanda }, item);
        }

        // PUT api/autenticacao/5
        [HttpPut("{id}")]
        public async Task<IActionResult> Put(int id, [FromBody] Banda item)
        {
            if (id != item.IdBanda)
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
            var todoItem = await RespositorioEspectador.DbSet.FindAsync(id);

            if (todoItem == null)
            {
                return NotFound();
            }

            RespositorioEspectador.DbSet.Remove(todoItem);
            await RespositorioEspectador.Contexto.SaveChangesAsync();

            return NoContent();
        }
    }
}

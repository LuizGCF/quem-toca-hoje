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
    [Route("api/evento")]
    [ApiController]
    public class EventoController : Controller
    {
        private readonly ContextoBanco Contexto;

        private RepositoryBase<Evento> repositorioespectador;

        public RepositoryBase<Evento> RespositorioEspectador
        {
            get
            {
                if (repositorioespectador == null)
                {
                    repositorioespectador = new RepositoryBase<Evento>(Contexto);
                }

                return repositorioespectador;
            }
            set { repositorioespectador = value; }
        }


        public EventoController(ContextoBanco contexto)
        {
            Contexto = contexto;
        }

        // GET api/autenticacao
        [HttpGet]
        public ActionResult<IEnumerable<Evento>> Get()
        {
            var item = RespositorioEspectador.DbSet.ToList();
            if (item == null)
                return NotFound();

            return item;
        }

        // GET api/autenticacao/5
        [HttpGet("{id}")]
        public async Task<ActionResult<Evento>> GetAsync(int id)
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
        public async Task<ActionResult<Evento>> PostAsync([FromBody] Evento item)
        {
            RespositorioEspectador.DbSet.Add(item);
            await RespositorioEspectador.Contexto.SaveChangesAsync();

            return CreatedAtAction(nameof(Evento), new { id = item.IdEvento }, item);
        }

        // PUT api/autenticacao/5
        [HttpPut("{id}")]
        public async Task<IActionResult> Put(int id, [FromBody] Evento item)
        {
            if (id != item.IdEvento)
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

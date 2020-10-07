using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using SimpleTCP;

namespace Servidor
{
    public partial class ServerFrm : Form
    {
        public ServerFrm()
        {
            InitializeComponent();
        }
        SimpleTcpServer server;

        private void ServerFrm_Load(object sender, EventArgs e)
        {
            server = new SimpleTcpServer();
            server.Delimiter = 0x13;//enter
            server.StringEncoder = Encoding.UTF8;
            server.DataReceived += Server_ReceibeDatos;
        }

        private void Server_ReceibeDatos(object sender, SimpleTCP.Message e)
        {
            // Actualiza mensaje en txtStatus
            txtStatus.Invoke((MethodInvoker)delegate ()
            {
                txtStatus.Text += e.MessageString;
                e.ReplyLine(string.Format("You said: {0}", e.MessageString));
            });
        }

        private void BtnStart_Click(object sender, EventArgs e)
        {
            //Inicia el servidor
            txtStatus.Text += "Iniciando servidor...";
            System.Net.IPAddress ip = System.Net.IPAddress.Parse(txtHost.Text);
            server.Start(ip, Convert.ToInt32(txtPort.Text));
        }

        private void BtnStop_Click(object sender, EventArgs e)
        {
            if (server.IsStarted)
                server.Stop();
        }
    }
}

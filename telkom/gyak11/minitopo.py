#!/usr/bin/python

from mininet.net import Mininet
from mininet.node import Controller, RemoteController, OVSController
from mininet.node import CPULimitedHost, Host, Node
from mininet.nodelib import LinuxBridge #OVSKernelSwitch, UserSwitch
from mininet.node import IVSSwitch
from mininet.cli import CLI
from mininet.log import setLogLevel, info
from mininet.link import TCLink, Intf
from subprocess import call

def myNetwork():

    net = Mininet( topo=None,
                   build=False,
                   ipBase='10.0.0.0/8')

    info( '*** Adding controller\n' )
    info( '*** Add switches\n')
    r5 = net.addHost('r5', cls=Node, ip='0.0.0.0')
    r5.cmd('sysctl -w net.ipv4.ip_forward=1')
    s4 = net.addSwitch('s4', cls=LinuxBridge, failMode='standalone')
    r6 = net.addHost('r6', cls=Node, ip='0.0.0.0')
    r6.cmd('sysctl -w net.ipv4.ip_forward=1')
    r7 = net.addHost('r7', cls=Node, ip='0.0.0.0')
    r7.cmd('sysctl -w net.ipv4.ip_forward=1')
    s3 = net.addSwitch('s3', cls=LinuxBridge, failMode='standalone')

    info( '*** Add hosts\n')
    h4 = net.addHost('h4', cls=Host, ip='11.0.4.1/8', defaultRoute=None)
    h2 = net.addHost('h2', cls=Host, ip='10.0.2.1/8', defaultRoute=None)
    h1 = net.addHost('h1', cls=Host, ip='10.0.1.1/8', defaultRoute=None)
    h3 = net.addHost('h3', cls=Host, ip='10.0.3.1/8', defaultRoute=None)
    h7 = net.addHost('h7', cls=Host, ip='12.0.7.1/8', defaultRoute=None)
    h6 = net.addHost('h6', cls=Host, ip='11.0.6.1/8', defaultRoute=None)
    h5 = net.addHost('h5', cls=Host, ip='11.0.5.1/8', defaultRoute=None)

    info( '*** Add links\n')
    net.addLink(h1, s3)
    net.addLink(h2, s3)
    net.addLink(h3, s3)
    net.addLink(s3, r5)
    net.addLink(s3, r6)
    net.addLink(r6, r7)
    net.addLink(r5, s4)
    net.addLink(r6, s4)
    net.addLink(r7, h7)
    net.addLink(s4, h4)
    net.addLink(s4, h5)
    net.addLink(s4, h6)

    info( '*** Starting network\n')
    net.build()
    info( '*** Starting controllers\n')
    for controller in net.controllers:
        controller.start()

    info( '*** Starting switches\n')
    net.get('s4').start([])
    net.get('s3').start([])

    info( '*** Post configure switches and hosts\n')

    CLI(net)
    net.stop()

if __name__ == '__main__':
    setLogLevel( 'info' )
    myNetwork()
